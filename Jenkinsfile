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
        
        string (
            description: 'Enter a build number (e.g. 1.12.4)', 
            name: 'BUILD', 
            trim: true
            )
        }
    
    stages {
        
        stage('Build') {
            steps {
                // Get the code from a GitHub repository
                git branch: "${params.BRANCH}", url: 'https://github.com/GigaSpaces-ProfessionalServices/TAU-Services.git'
                sh "mvn clean install -f ${params.BRANCH}/pom-runtime.xml"
            }
        }    
    
        stage('Deploy') {
            steps {
                sh "sudo chmod +x service.py"
                sh "python -u service.py undeploy ${ENVIRONMENT} ${BRANCH}"
                sh "./service.py deploy ${ENVIRONMENT} ${BRANCH}"
            }
        }
    }
}
