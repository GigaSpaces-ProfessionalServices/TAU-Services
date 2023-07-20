pipeline {
    
    agent any
    
    environment {
        GIT_URL = 'https://github.com/GigaSpaces-ProfessionalServices/TAU-Services.git'
        GIT_CREDS = ''
    }

    parameters {
        
        choice (
            choices: ['Development', 'Stage', 'Production'], 
            description: 'Choose the preferred environment for deployment', 
            name: 'ENVIRONMENT'
            )
        
        gitParameter (
            branch: '', 
            branchFilter: 'origin/(?!main)(.*)', 
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
        
        stage('Build') {
            steps {
                //git branch: "${params.BRANCH}", url: 'https://tau-gitlab.tau.ac.il/tau-strategy/dih.git', credentialsId: '80939591-33cc-41a9-b839-756e39e6f34d'
                git branch: "${params.BRANCH}", url: ${GIT_URL}, credentialsId: ${GIT_CREDS}
                script {
                    try {
                        sh "mvn clean install"
                        sh "mvn clean install -f ${params.BRANCH}/pom-runtime.xml"
                    } catch (Exception e) {
                        error("Failed to execute 'mvn install'")
                    }
                }
            }
        }

        stage('Setup') {
            steps {
                // get ssl certificates and service script
                dir('__main__') {
                    checkout scm
                }
                sh "mv __main__/ssl ./"
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
            steps {
                sh "python3 -u service.py undeploy ${ENVIRONMENT} ${BRANCH}"
            }
        }
        
        stage('Deploy') {
            steps {
                sh "python3 -u service.py deploy ${ENVIRONMENT} ${BRANCH}"
            }
        }
        
        stage('Test') {
            steps {
                sh "python3 -u service.py test ${ENVIRONMENT} ${BRANCH}"
            }
        }
    }
    // post {
    //     success {
    //         // send resource file to a file repository (S3, NFS etc...)
    //     }
    // }
}
