pipeline {
    
    agent any
    
    stages {
        // stage('Build') {
        //     steps {
        //         // Get the code from a GitHub repository
        //         git branch: "${branch_name}", url: "https://github.com/GigaSpaces-ProfessionalServices/TAU-Services.git"
        //         sh "mvn clean install -f ${SERVICE_NAME}/pom-runtime.xml"
        //     }
        // }
        stage('Test') {
            steps {
                // Test build
                echo "Testing..."
            }
        }
        stage('Cleanup') {
            steps {
                // Undeploy assets
                echo "Undeploying ..."
            }
        }
        stage('Deploy') {
            steps {
                sh "echo \"THIS IS a MB pipeline\""
                // // create containers //
                // sh """ #!/bin/bash
                // curl -X POST --header 'Content-Type: application/json' --header 'Accept: text/plain' \
                // -d '{"memory":"1g", "zone":"${SERVICE_NAME}", "host":"${SPACE}"}' \
                // 'http://${MANAGER}:8090/v2/containers'
                // """
                
                // // upload resource //
                // sh """ #!/bin/bash
                // resource=\$(find \${SERVICE_NAME}/target -name "*\${SERVICE_NAME}*dependencies.jar")
                // curl -X PUT -F "file=@\${resource}" \
                // 'http://${MANAGER}:8090/v2/pus/resources'
                // """
                
                // // create pu //
                // sh """ #!/bin/bash
                // curl -X POST --header 'Content-Type: application/json' --header 'Accept: text/plain' \
                // -d '{"resource":"Person_Tziun_Kurs-1.1-SNAPSHOT-jar-with-dependencies.jar", "topology":{"instances":1}, "name":"Person_Tziun_Kurs", \
                // "sla":{"zones":["Person_Tziun_Kurs"]}}' \
                // 'http://${MANAGER}:8090/v2/pus'
                // """
            }
        }
    }
}
