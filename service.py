#!/usr/bin/python3
# *-* coding: utf-8 *-*

import requests
import sys
import re
import yaml
import subprocess
from time import sleep
from glob import glob


def usage():
    print(f"[INFO] usage: {__file__} [deploy | undeploy] environment service")


def pu_exist(service_name):
    base_url = f'http://{manager}:8090/v2/pus'
    h = {'Accept': 'application/json'}
    data = requests.get(base_url, headers=h, verify=False).json()
    for pu in data:
        if service_name == pu['name']:
            return True
    return False


def pu_intact(service_name):
    base_url = f'http://{manager}:8090/v2/pus'
    h = {'Accept': 'application/json'}
    data = requests.get(base_url, headers=h, verify=False).json()
    for pu in data:
        if service_name == pu['name'] and pu['status'] == 'intact':
            return True
    return False


def get_service_containers(service_name):
    base_url = f'http://{manager}:8090/v2/containers'
    h = {'Accept': 'application/json'}
    data = requests.get(base_url, headers=h, verify=False).json()
    cont_ids = [cont['id'] for cont in data if cont['zones'][0] == service_name]
    return cont_ids


def get_service_resource(service_name):
    h = {'Accept': 'application/json'}
    base_url = f'http://{manager}:8090/v2/pus'
    data = requests.get(f"{base_url}/resources", headers=h, verify=False).json()
    for item in data:
        if service in item:
            return item


def get_request_desc(request_id):
    data = requests.get(
        f'http://{manager}:8090/v2/requests?status=All', 
        headers={'Accept': 'application/json'}, 
        verify=False).json()
    for item in data:
        if item['id'] == request_id:
            return item['description']
    return None


def is_rest_ok(manager):
    url = f'http://{manager}:8090/v2/index.html'
    try:
        if requests.get(url, verify=False, timeout=3).status_code == 200:
            return True
    except:
        return False


def undeploy_service():
    # undeploy pu
    base_url = f'http://{manager}:8090/v2/pus'
    h = {'Accept': 'application/json'}
    if pu_exist(service):
        h = {'Accept': 'text/plain'}
        del_pu_data = requests.delete(f"{base_url}/{service}?keepFile=false", headers=h, verify=False)
        if del_pu_data.status_code in range(200,300):
            status, count = False, 0
            while count < 3:
                if not pu_exist(service):
                    status = True
                    break
                count += 1
                sleep(1)
        print(f"[INFO] operation: {get_request_desc(del_pu_data.text)} (id: {del_pu_data.text})")
        if status:
            while pu_exist(service):
                sleep(1)
            print("[INFO] undeployed processing unit successfully")
        else:
            print("[ERROR] undeploy processing unit failed")
    else:
        print("[INFO] no processing unit(s) exist. skipping")

    # kill containers
    containers = get_service_containers(service)
    if len(containers) == 0:
        print(f"[INFO] no containers found for service '{service}'")
    else:
        count, limit = 0, 3
        while count < limit:        
            if len(containers) > 0:
                h = {'Accept': 'text/plain'}
                base_url = f'http://{manager}:8090/v2/containers'
                for id in containers:
                    del_cont_data = requests.delete(f"{base_url}/{id}", headers=h, verify=False)
                    sleep(1)
                    print(f"[INFO] operation: {get_request_desc(del_cont_data.text)} (id: {del_cont_data.text})")
            else:
                print(f"[INFO] deleted all containers for service '{service}' successfully")
                break
            containers = get_service_containers(service)
            count += 1
        if count == limit:
            print(f"[ERROR] delete all containers for service '{service}' failed - timeout exceeded")

    # delete resource
    h = {'Accept': 'application/json'}
    base_url = f'http://{manager}:8090/v2/pus'
    data = requests.get(f"{base_url}/resources", headers=h, verify=False).json()
    resource_name = get_service_resource(service)
    if resource_name is not None:
        h = {'Accept': 'text/plain'}
        del_resource_data = requests.delete(f"{base_url}/resources/{resource_name}", headers=h, verify=False)
        sleep(1)
        print(f"[INFO] operation: {get_request_desc(del_resource_data.text)} (id: {del_resource_data.text})")
        if int(del_resource_data.status_code) in range(200,300):
            print(f"[INFO] deleted resource '{resource_name}' successfully")
        if int(del_resource_data.status_code) in range(400, 500):
            if del_resource_data.status_code == 423:
                print(f"[ERROR] delete resource '{resource_name}' failed - resource is currently in use")
    else:
        print(f"[INFO] no resources found for service '{service}'")
    print()


def deploy_service():
    # create containers
    h = {'Accept': 'text/plain', 'Content-Type': 'application/json'}
    base_url = f'http://{manager}:8090/v2/containers'
    for host in SPACE_HOSTS:
        payload = str({"memory": "1g", "zone": service, "host": host}).replace("'",'"')
        create_cont_data = requests.post(base_url, data=payload, headers=h)
        print(f"[INFO] operation: {get_request_desc(create_cont_data.text)} (id: {create_cont_data.text})")
        if int(create_cont_data.status_code) in range(200,300):
            print(f"[INFO] container created successfully")
        else:
            print(f"[ERROR] container creation failed")

    # upload resource
    base_url = f'http://{manager}:8090/v2/pus'
    resource = glob(f"{service}/target/*{service}*dependencies.jar")[0]
    upload = requests.put(
        f"{base_url}/resources", 
        files={'file': open(resource, 'rb')}, 
        verify=False)
    print(f"[INFO] operation: Upload resource (id: {upload.text})")
    if int(upload.status_code) in range(200,300):
        print(f"[INFO] resource uploaded successfully")
    else:
        print(f"[ERROR] resource upload failed")

    # deploy pu
    resource_name = get_service_resource(service)
    base_url = f'http://{manager}:8090/v2/pus'
    h = {'Accept': 'text/plain', 'Content-Type': 'application/json'}
    payload = str({
        "resource": resource_name, 
        "topology":{"instances": 1}, 
        "name":service, 
        "sla": {"zones":[service]}, 
        "contextProperties":{
            "consul.host": "localhost", 
            "minPort": 8115, 
            "maxPort": 8311,
            "space": "dih-tau-space"
        }
        }).replace("'",'"')
    deploy_pu_data = requests.post(base_url, data=payload, headers=h)
    while deploy_pu_data.text is None:
        sleep(1)
    print(f"[INFO] operation: {get_request_desc(deploy_pu_data.text)} (id: {deploy_pu_data.text})")
    if deploy_pu_data.status_code in range(200,300):
        print("[INFO] deployed processing unit successfully")
        print(f"[INFO] waiting for service to instantiate...")
        count, timeout = 0, 60
        while count < timeout:
            if pu_intact(service):
                break
            else:
                count += 1
                sleep(1)
        if count == timeout:
            print("[WARNING] deploy processing unit state unknown - timeout exceeded. check the logs or re-deploy")
        else:
            print(f"[INFO] service initialized successfully (in {count}sec)")
    else:
        print("[ERROR] deploy processing unit failed")
    print()


if __name__ == '__main__':

    if len(sys.argv) < 4:
        print("[ERROR] missing required parameters")
        usage()
        exit(1)
    else:
        opt = sys.argv[1]
        env_name = sys.argv[2]
        service = sys.argv[3]
    
    # parse environment
    if env_name == "Development":
        env_key = 'DEV'
    elif env_name == "Stage":
        env_key = 'STG'
    elif env_name == "Production":
        env_key = 'PRD'
    else:
        env_key = ''

    # get pivot for selected env_name
    if env_key == '':
        print("[ERROR] invalid env name specified. check service.py configuration.")
        exit(1)
    with open('/etc/environment', 'r', encoding='utf-8') as env_file:
        for line in env_file:
            l = line.rstrip('\n')
            if re.search(env_key, l):
                pivot = l.split('=')[1]
    if pivot == "":
        print("[ERROR] pivot is not set in /etc/environment for selected env_name")
        exit(1)

    # load servers from host.yaml
    cmd = f"sudo su - root -c \"ssh {pivot} 'cat \$ENV_CONFIG/host.yaml'\""
    hosts = yaml.safe_load(subprocess.run(cmd, shell=True, stdout=subprocess.PIPE).stdout.decode())
    
    # parse management hosts and set operational manager
    MANAGER_HOSTS = [m for m in hosts['servers']['manager'].values()]
    if len(MANAGER_HOSTS) == 0:
        print("[ERROR] DIH managers not defined. check host.yaml configuration on pivot host.")
        exit(1)
    restful_managers = [m for m in MANAGER_HOSTS if is_rest_ok(m)]
    manager = restful_managers[0]

    # parse space hosts
    SPACE_HOSTS = [m for m in hosts['servers']['space'].values()]
    if len(SPACE_HOSTS) == 0:
        print("[ERROR] DIH spaces not defined. check host.yaml configuration on pivot host.")
        exit(1)
    
    if opt == 'deploy':
        deploy_service()
    elif opt == 'undeploy':
        undeploy_service()
    else:
        usage()
