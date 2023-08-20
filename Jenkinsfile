pipeline {
    
    agent any
    
    environment {
        GIT_URL = 'https://tau-gitlab.tau.ac.il/tau-strategy/dih.git'
        GIT_CREDS = '80939591-33cc-41a9-b839-756e39e6f34d'
    }

    stages {
        stage('Parameters') {
            steps {
                script { 
                    properties(
                        [
                            parameters(
                                [
                                    [
                                        $class: 'ChoiceParameter', 
                                        choiceType: 'PT_SINGLE_SELECT',
                                        description: 'Select an action to execute', 
                                        filterLength: 1, 
                                        filterable: false, 
                                        name: 'ACTION', 
                                        script: 
                                        [
                                            $class: 'GroovyScript', 
                                            fallbackScript: 
                                            [
                                                classpath: [], 
                                                sandbox: true, 
                                                script: 'return ["Could not get action type"]'
                                            ], 
                                            script: 
                                            [
                                                classpath: [], 
                                                sandbox: true, 
                                                script: 'return [":selected", "Create", "Deploy"]'
                                            ]
                                        ]
                                    ]
                                ]
                            )
                        ]
                    )
                }
            }
        }
    }   
}
