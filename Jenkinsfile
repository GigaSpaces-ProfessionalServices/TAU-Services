pipeline {
    
    agent any
    
    environment {
        GIT_URL = 'https://github.com/GigaSpaces-ProfessionalServices/TAU-Services.git'
        GIT_CREDS = ''
    }

    parameters {
        choice (
            choices: ['Create', 'Deploy', 'Create and deploy'], 
            description: 'Choose your preferred actions for a new service', 
            name: 'OPT'
            )
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
        
        stage('dev') {
            steps {
                script {
                    if ( OPT == 'Create' ) {
                        sh "echo \"Environment Create = ${params.ENVIRONMENT}\""
                    }
                    if ( OPT == 'Deploy' ) {
                        sh "echo \"Environment Deploy = ${params.ENVIRONMENT}\""
                    }
                    if ( OPT == 'Create and deploy' ) {
                        sh "echo \"Environment Both = ${params.ENVIRONMENT}\""
                    }
                }
                // sh "echo \"Environment = ${params.ENVIRONMENT}\""
                // sh "echo \"Env = ${params.env}\""
                // sh "echo \"Target = ${params.target}\""
            }
        }

        // stage('Build') {
        //     steps {
        //         git branch: "${params.BRANCH}", url: "${env.GIT_URL}", credentialsId: "${env.GIT_CREDS}"
        //         script {
        //             try {
        //                 sh "mvn clean install"
        //             } catch (Exception e) {
        //                 error("Failed to execute 'mvn install'")
        //             }
        //         }
        //     }
        // }

        // stage('Setup') {
        //     steps {
        //         // get ssl certificates and service script
        //         dir('__main__') {
        //             checkout scm
        //         }
        //         sh "if [ -d ssl ]; then rm -rf ./ssl ; fi"
        //         sh "mv __main__/ssl ./"
        //         sh "if [ -d service.py ]; then rm -f ./service.py ; fi"
        //         sh "mv __main__/service.py ./"
        //         sh "rm -rf __main__*"
        //         sh "sudo chmod +x service.py"
                
        //         // set build version for jar
        //         sh '''
        //         for f in $(ls \${WORKSPACE}/\${BRANCH}/target/*.jar); do
        //             mv \${f} "\$(echo \${f} | sed "s/SNAPSHOT/\${BUILD_NUMBER}/")"
        //         done
        //         '''
        //     }
        // }

        // stage('Undeploy') {
        //     steps {
        //         script {
        //             try {
        //                 sh "python3 -u service.py undeploy ${ENVIRONMENT} ${BRANCH}"
        //             } catch (Exception e) {
        //                 error("Failed to undeploy service")
        //             }
        //         }
        //     }
        // }
        
        // stage('Deploy') {
        //     steps {
        //         script {
        //             try {
        //                 sh "python3 -u service.py deploy ${ENVIRONMENT} ${BRANCH}"
        //             } catch (Exception e) {
        //                 error("Failed to deploy service")
        //             }
        //         }
        //     }
        // }
        
        // stage('Test') {
        //     steps {
        //         sh "python3 -u service.py test ${ENVIRONMENT} ${BRANCH}"
        //     }
        // }
    }
    // post {
    //     success {
    //         // send resource file to a file repository (S3, NFS etc...)
    //     }
    // }
}
