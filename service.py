#!/usr/bin/python3
# *-* coding: utf-8 *-*

import requests
import sys
import os
import re
import socket
import yaml
import subprocess
from time import sleep
from glob import glob


def usage():
    print("\nUsage:")
    print(f"  {os.path.basename(__file__)} [command] environment [parameters: service_name | query_type [types | fields [TYPE_NAME]]]")
    print("\nCommand:")
    print(f"{'  deploy':<12}{'-':<5} deploy a service")
    print(f"{'  undeploy':<12}{'-':<5} undeploy a service")
    print(f"{'  test':<12}{'-':<5} test service health endpoint")
    print(f"{'  query':<12}{'-':<5} query space data")
    print("\nParameters:")
    print(f"{'  service:':<12}{'-':<5} the service name required when command is not a query")
    print(f"{'  type:':<12}{'-':<5} the type of query. on of (types | fields)")
    print('\n')


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


def get_space_types(space_id):
    # return a list of types in space
    h = {'Accept': 'application/json'}
    base_url = f'http://{manager}:8090/v2/spaces/{space_id}/statistics/types'
    types = [t for t in requests.get(base_url, headers=h, verify=False).json()]
    return types


def get_space_type_fields(space_id, _type):
    # return a list of types in space
    h = {'Accept': 'application/json'}
    base_url = f'http://{manager}:8090/v2/spaces/{space_id}/objectsTypeInfo'
    for obj in requests.get(base_url, headers=h, verify=False).json()['objectTypesMetadata']:
        if obj['objectName'] == _type:
            fields = [f['name'] for f in obj['schema']]
            return fields


def get_request_desc(request_id):
    data = requests.get(
        f'http://{manager}:8090/v2/requests?status=All',
        headers={'Accept': 'application/json'},
        verify=False).json()
    for item in data:
        if item['id'] == request_id:
            return item['description']
    return None


def check_connection(_server, _port, _timeout=5):
    check_port = 1
    a_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    a_socket.settimeout(_timeout)
    try:
        check_port = a_socket.connect_ex((_server, _port))
    except socket.error as socerr:
        print(f"[ERROR] caught exception: {socerr}")
    a_socket.settimeout(None)
    return check_port == 0


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
    for host in spaces:
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
            "space": f"{space_name}"
        }
        }).replace("'",'"')
    deploy_pu_data = requests.post(base_url, data=payload, headers=h)
    sleep(1)
    if deploy_pu_data.text is None:
        sleep(3)
    print(f"[INFO] operation: {get_request_desc(deploy_pu_data.text)} (id: {deploy_pu_data.text})")
    if deploy_pu_data.status_code in range(200,300):
        print("[INFO] deployed processing unit successfully")
        print(f"[INFO] waiting for service to instantiate...")
        count = 0
        init_timeout = 300
        while count < init_timeout:
            if pu_intact(service):
                break
            else:
                count += 1
                sleep(1)
        if count == init_timeout:
            print(f"[WARNING] deploy timeout ({init_timeout}s) exceeded - state unknown.")
            exit(1)
        else:
            print(f"[INFO] service initialized successfully (in {count}sec)")
    else:
        print("[ERROR] deploy processing unit failed")
    print()


def test_service_health():

    def get_ssl_file(_type):
        if os.path.exists(ssl_root):
            try:
                the_file = glob(f"{ssl_root}/{_type}/*")[0]
            except:
                print(f"[ERROR] {_type} file could not be found in '{ssl_root}/{_type}/'.")
        return the_file

    # get northbound applicative domain
    cmd = f"sudo su - root -c \"ssh {pivot} 'cat \$ENV_CONFIG/nb/applicative/nb.conf.template' | grep \"NB_DOMAIN\"\""
    nbconf = subprocess.run(cmd, shell=True, stdout=subprocess.PIPE).stdout.decode()
    nb_domain = str(nbconf).rstrip('\n').replace('"','').split('=')[1]
    base_url = f'https://{nb_domain}:8443/{service}/v1/actuator/health'
    ssl_root = f"ssl/{env_name}"

    ca_file = get_ssl_file('ca')
    cert_file = get_ssl_file('cert')
    key_file = get_ssl_file('key')

    for c in [ca_file, cert_file, key_file]:
        if c is None:
            print("[ERROR] missing required ssl files. aborting health check!")

    # execute health check
    count, timeout = 1, 20
    proxy = {
        "http": "http://132.66.251.5:8080",
        "https": "http://132.66.251.5:8080"
    }
    print(f"[INFO] Checking service '{service}' health ...")
    while count < timeout:
        count += 1
        try:
            if len(proxy) > 0:
                response = requests.get(base_url, proxies=proxy, cert=(cert_file, key_file), verify=ca_file, timeout=5)
            else:
                response = requests.get(base_url, cert=(cert_file, key_file), verify=ca_file, timeout=5)
        except requests.exceptions.SSLError as serr:
            print(f"[ERROR]: request status - SSLError: \n{serr}")
            continue
        except requests.exceptions.Timeout:
            print("[WARNING]: request status - timeout reached.")
            sleep(1)
            continue
        except requests.exceptions.TooManyRedirects:
            print("[WARNING]: request status - too many redirects.")
            sleep(1)
            continue
        except requests.exceptions.RequestException as e:
            print(f"[WARNING]: request status - exception: \n{e}")
            continue
        else:
            if response.status_code == 200:
                text = response.text.replace('"','')
                print(f"[INFO ] service '{service}' health check returned: {text}")
                break
            sleep(1)


def query_space(_query_type):
    if _query_type == 'types':
        #print(get_space_types(space_name))
        rlist = '\n'.join(get_space_types(space_name))
        #rlist = 'aaa\nbbb\nccc'
        print(rlist)
    if _query_type == 'fields':
        rlist = '\n'.join(get_space_type_fields(space_name, field_type))
        print(rlist)



if __name__ == '__main__':

    if len(sys.argv) < 4:
        print("[ERROR] missing required parameters")
        usage()
        exit(1)
    opt = sys.argv[1]
    env_name = sys.argv[2]
    if opt != 'query':
        service = sys.argv[3]
    else:
        qtype = sys.argv[3]

    if qtype == 'fields':
        if len(sys.argv) < 5:
            print("[ERROR] missing TYPE_NAME parameter for fields query")
            usage()
            exit(1)
        else:
            field_type = sys.argv[4]

    # the space id
    space_name = "dih-tau-space"

    # parse environment
    if env_name.casefold() == "development":
        env_key = 'TAUG'
    elif env_name.casefold() == "stage":
        env_key = 'TAUS'
    elif env_name.casefold() == "production":
        env_key = 'TAUP'
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

    # check connection to pivot
    if not check_connection(pivot, 22):
        print(f"[ERROR] unable to connect via SSH to pivot '{pivot}'. operation aborted!")
        exit(1)

    # load servers from host.yaml
    cmd = f"sudo su - root -c \"ssh {pivot} 'cat \$ENV_CONFIG/host.yaml'\""
    hosts = yaml.safe_load(subprocess.run(cmd, shell=True, stdout=subprocess.PIPE).stdout.decode())

    # get management hosts and set operational manager
    managers = [m for m in hosts['servers']['manager'].values()]
    if len(managers) == 0:
        print("[ERROR] DIH managers not defined. check host.yaml configuration on pivot host.")
        exit(1)
    restful_managers = [m for m in managers if is_rest_ok(m)]
    manager = restful_managers[0]   # we use the 1st available manager

    # get space hosts
    spaces = [m for m in hosts['servers']['space'].values()]
    if len(spaces) == 0:
        print("[ERROR] DIH spaces not defined. check host.yaml configuration on pivot host.")
        exit(1)

    # parse parameters
    if opt == 'deploy':
        deploy_service()
    elif opt == 'undeploy':
        undeploy_service()
    elif opt == 'test':
        test_service_health()
    elif opt == 'query':
        query_space(qtype)
    else:
        usage()
