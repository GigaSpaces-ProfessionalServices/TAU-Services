pipeline {
    
    agent any
    
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
                git branch: "${params.BRANCH}", url: 'https://tau-gitlab.tau.ac.il/tau-strategy/dih.git', credentialsId: '80939591-33cc-41a9-b839-756e39e6f34d'
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

        stage('Set build version') {
            steps {
                sh '''
                for f in $(ls \${WORKSPACE}/\${BRANCH}/target/*.jar); do
                    mv \${f} "\$(echo \${f} | sed "s/SNAPSHOT/\${BUILD_NUMBER}/")"
                done
                '''
            }
        }

        stage('Undeploy') {
            steps {
                sh "sudo chmod +x service.py"
                sh "python3 -u service.py undeploy ${ENVIRONMENT} ${BRANCH}"
            }
        }
        
        stage('Deploy') {
            steps {
                sh "python3 -u service.py deploy ${ENVIRONMENT} ${BRANCH}"
                // get ssl certificates into selected branch
                dir('__main__') {
                    checkout scm
                }
                sh "mv __main__/ssl . && rm -rf __main__*"
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
