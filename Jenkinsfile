pipeline {
    
    agent any
    
    environment {
        GIT_URL = 'https://tau-gitlab.tau.ac.il/tau-strategy/dih.git'
        GIT_CREDS = '80939591-33cc-41a9-b839-756e39e6f34d'
    }

    parameters {
        // choice (
        //     choices: ['Create', 'Deploy'], 
        //     description: 'Choose action type', 
        //     name: 'ACTION'
        // )

        // string (
        //     defaultValue: '', 
        //     description: '*** Required for CREATE action only ***',
        //     name: 'SERVICE_NAME', 
        //     trim: true
        // )

        choice (
            choices: ['Development', 'Stage', 'Production'], 
            description: 'Choose a target environment', 
            name: 'ENVIRONMENT'
        )
        
        gitParameter (
            branch: '', 
            branchFilter: 'origin/(?![main|blueprint])(.*)', 
            defaultValue: '', 
            description: 'Choose the target branch', 
            listSize: '0', 
            name: 'BRANCH', 
            quickFilterEnabled: true, 
            requiredParameter: true, 
            selectedValue: 'NONE', 
            sortMode: 'NONE', 
            tagFilter: '*', 
            type: 'PT_BRANCH'
        )
    }

    stages {
        // stage('User Selections') {
        //     steps {
        //         script {
        //             env.CREATE_OK = false
        //             env.DEPLOY_OK = false
        //             if (params.ACTION == 'Create') {
        //                 if (params.SERVICE_NAME != '') {
        //                     env.CREATE_OK = true
        //                 }
        //                 else {
        //                     echo "[ERROR] selected ACTION: 'Create' missing SERVICE_NAME."
        //                 }
        //             }
        //             if (params.ACTION == 'Deploy') {
        //                 env.DEPLOY_OK = true
        //             }
        //         }
        //     }
        // }
    
        // stage('Service Creation') {
        //     when {
        //         expression { return env.CREATE_OK.toBoolean() }
        //     }
        //     steps {
        //         sh "echo \"running stage: CREATE for service: $SERVICE_NAME\""
        //     }
        // }

        // stage('Service Deployment') {
        //     when {
        //         expression { return env.DEPLOY_OK.toBoolean() }
        //     }
            
        //     steps {
        //         sh "echo \"running stage: DEPLOY service ${params.BRANCH} on environment: ${params.ENVIRONMENT}\""
        //     }
        // }

        // stage('Get Types') {
        //     when {
        //         expression { return env.CREATE_OK.toBoolean() }
        //     }
            
        //     steps {
        //         echo "Selected option: ${params.SELECTED_OPTION}"
        //     }
        //     // env.TYPES = sh(script:'python3 -u service.py query ${ENVIRONMENT} types', returnStdout: true)
        //     // sh "echo ${env.TYPES}"
        // }

        stage('Build') {
            // when {
            //     expression { return env.DEPLOY_OK.toBoolean() }
            // }
            steps {
                git branch: "${params.BRANCH}", url: "${env.GIT_URL}", credentialsId: "${env.GIT_CREDS}"
                script {
                    try {
                        sh "mvn clean install"
                    } catch (Exception e) {
                        error("Failed to execute 'mvn install'")
                    }
                }
            }
        }

        stage('Setup') {
            // when {
            //     expression { return env.DEPLOY_OK.toBoolean() }
            // }
            steps {
                // get ssl certificates and service script
                dir('__main__') {
                    checkout scm
                }
                sh "if [ -d ssl ]; then rm -rf ./ssl ; fi"
                sh "mv __main__/ssl ./"
                sh "if [ -d service.py ]; then rm -f ./service.py ; fi"
                sh "mv __main__/service.py ./"
                sh "rm -rf __main__*"
                sh "sudo chmod +x service.py"
                
                // set build version for jar
                sh '''
                for f in $(ls \${WORKSPACE}/\${BRANCH}/target/*.jar); do
                    mv \${f} "\$(echo \${f} | sed "s/SNAPSHOT/\${BUILD_NUMBER}/")"
                done
                '''
            }
        }

        stage('Undeploy') {
            // when {
            //     expression { return env.DEPLOY_OK.toBoolean() }
            // }
            steps {
                script {
                    try {
                        sh "python3 -u service.py undeploy ${ENVIRONMENT} ${BRANCH}"
                    } catch (Exception e) {
                        error("Failed to undeploy service")
                    }
                }
            }
        }

        stage('Deploy') {
            // when {
            //     expression { return env.DEPLOY_OK.toBoolean() }
            // }
            steps {
                script {
                    try {
                        sh "python3 -u service.py deploy ${ENVIRONMENT} ${BRANCH}"
                    } catch (Exception e) {
                        error("Failed to deploy service")
                    }
                }
            }
        }

        stage('Test') {
            // when {
            //     expression { return env.DEPLOY_OK.toBoolean() }
            // }
            steps {
                sh "python3 -u service.py test ${ENVIRONMENT} ${BRANCH}"
            }
        }
    
        // post {
        //     success {
        //         // send resource file to a file repository (S3, NFS etc...)
        //     }
        // }
    }
    
}
