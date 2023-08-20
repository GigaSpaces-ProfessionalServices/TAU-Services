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
                                                script: '''
                                                def command = ['cat', '/tmp/file1']
                                                def proc = command.execute()
                                                proc.waitFor()              
                                                if (outputFile.exists()) {
                                                    def outputContent = outputFile.text
                                                    println "Git branch list: ${outputContent}"
                                                } else {
                                                    println "Output file not found."
                                                }
                                                def output = proc.in.text
                                                def exitcode= proc.exitValue()
                                                def error = proc.err.text
                                                if (error) {
                                                    println "Std Err: ${error}"
                                                    println "Process exit code: ${exitcode}"
                                                    return exitcode
                                                }
                                                //println output.split()
                                                //return output.tokenize()
                                                return output.join()
                                                '''
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
