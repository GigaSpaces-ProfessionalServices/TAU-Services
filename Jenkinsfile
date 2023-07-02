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
            branchFilter: 'origin/(.*)', 
            defaultValue: '', 
            description: 'Choose the target branch', 
            listSize: '0', 
            name: 'BRANCH', 
            quickFilterEnabled: false, 
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
                // Get the code from a GitHub repository
                git branch: "${params.BRANCH}", url: 'https://github.com/GigaSpaces-ProfessionalServices/TAU-Services.git'
                sh "mvn clean install -f ${params.BRANCH}/pom-runtime.xml"
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
            }
        }
    }
    // post {
    //     success {
    //         // send resource file to a file repository (S3, NFS etc...)
    //     }
    // }
}
