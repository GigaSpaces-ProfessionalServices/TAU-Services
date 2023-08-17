pipeline {
    
    agent any
    
    environment {
        GIT_URL = 'https://tau-gitlab.tau.ac.il/tau-strategy/dih.git'
        GIT_CREDS = '80939591-33cc-41a9-b839-756e39e6f34d'
    }

    // parameters {
    //     choice (
    //         choices: ['Create', 'Deploy'], 
    //         description: 'Choose action type', 
    //         name: 'ACTION'
    //     )

    //     string (
    //         defaultValue: '', 
    //         description: '*** Required for CREATE action only ***',
    //         name: 'SERVICE_NAME', 
    //         trim: true
    //     )

    //     choice (
    //         choices: ['Development', 'Stage', 'Production'], 
    //         description: 'Choose a target environment', 
    //         name: 'ENVIRONMENT'
    //     )
        
    //     gitParameter (
    //         branch: '', 
    //         branchFilter: 'origin/(?![main|blueprint])(.*)', 
    //         defaultValue: '', 
    //         description: 'Choose the target branch', 
    //         listSize: '0', 
    //         name: 'BRANCH', 
    //         quickFilterEnabled: true, 
    //         requiredParameter: true, 
    //         selectedValue: 'NONE', 
    //         sortMode: 'NONE', 
    //         tagFilter: '*', 
    //         type: 'PT_BRANCH'
    //     )
    // }

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
                                    ],
                                    [
                                        $class: 'DynamicReferenceParameter', 
                                        choiceType: 'ET_FORMATTED_HTML', 
                                        description: 'Enter a service name for the new service (not applicable for deploy action)', 
                                        name: 'SERVICE_NAME', 
                                        omitValueField: false, 
                                        randomName: 'choice-parameter-4155200957257890', 
                                        referencedParameters: 'ACTION', 
                                        script: 
                                        [
                                            $class: 'GroovyScript', 
                                            fallbackScript: 
                                            [
                                                classpath: [], 
                                                oldScript: '', 
                                                sandbox: true, 
                                                script: 'return "Please select an action!"'
                                            ], 
                                            script: 
                                            [
                                                classpath: [], 
                                                oldScript: '', 
                                                sandbox: true, 
                                                script: '''
                                                if(ACTION.equals("Create")) {
                                                    inputBox = "<input name=\'value\' class=\'setting-input\' type=\'text\'>"
                                                    return inputBox
                                                } else if(ACTION.equals("Deploy")){ return "" }'''
                                            ]
                                        ]
                                    ],
                                    [
                                        $class: 'ChoiceParameter', 
                                        choiceType: 'PT_RADIO', 
                                        description: 'Select a target environment', 
                                        filterLength: 1, 
                                        filterable: false, 
                                        name: 'ENV', 
                                        script: 
                                        [
                                            $class: 'GroovyScript', 
                                            fallbackScript: 
                                            [
                                                classpath: [], 
                                                sandbox: true, 
                                                script: 'return ["Could not get environment"]'
                                            ], 
                                            script: 
                                            [
                                                classpath: [], 
                                                sandbox: true, 
                                                script: 'return ["Development:selected", "Stage", "Production"]'
                                            ]
                                        ]
                                    ],
                                    [
                                        $class: 'CascadeChoiceParameter', 
                                        choiceType: 'PT_SINGLE_SELECT', 
                                        description: 'Select the branch from the dropdown List',
                                        name: 'BRANCH', 
                                        referencedParameters: '', 
                                        script: 
                                        [
                                            $class: 'GroovyScript', 
                                            fallbackScript: 
                                            [
                                                classpath: [], 
                                                sandbox: true, 
                                                script: 'return ["Could not get branch from BRANCH Param"]'
                                            ], 
                                            script: 
                                            [
                                                classpath: [], 
                                                sandbox: true, 
                                                // script: '''def choices = []
                                                // def processBuilder = new ProcessBuilder('/bin/bash', '-c', 'echo "Option 1" && echo "Option 2" && echo "Option 3"')
                                                // def process = processBuilder.start()
                                                // process.waitFor()
                                                // process.in.eachLine { line ->
                                                //     choices.add(line.trim())
                                                // }
                                                // return choices
                                                // '''
                                                script: '''
def git_cmd = 'echo ' + params.WORKSPACE
def outputFile = new File('/tmp/output.txt')

def processBuilder = new ProcessBuilder('/bin/bash', '-c', git_cmd)
processBuilder.redirectOutput(ProcessBuilder.Redirect.to(outputFile))
def process = processBuilder.start()
process.waitFor()

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

                                                // script: '''def branches = []
                                                // def gitBranches = 'git branch -r'.execute().text
                                                // gitBranches.eachLine { line ->
                                                //     def branch = line.trim()
                                                //     if (branch.startsWith('origin/')) {
                                                //         branches.add(branch.substring('origin/'.length()))
                                                //     }
                                                // }
                                                // return branches.join(',')
                                                // '''

                                                // script: '''def branches = []
                                                // def gitBranches = 'git branch -r'.execute().text
                                                // gitBranches.eachLine { line ->
                                                //     def branch = line.trim()
                                                //     if (branch.startsWith('origin/')) {
                                                //         branches.add(branch.substring('origin/'.length()))
                                                //     }
                                                // }
                                                // return branches
                                                // '''
                                            ]
                                        ]
                                    ]
                                ]
                            )
                        ]
                    )
                //echo ENV
                //echo BRANCH
                }
            }
        }
        // stage('User Selections') {
        //     steps {
        //         script {
        //             println WORKSPACE
        //             // def branches = sh(script: 'git branch -r')
        //             // echo branches

        //             def command = 'echo "Hello, world!"'  // Replace with your Groovy shell command
        //             def outputFile = new File('/tmp/output.txt')

        //             def processBuilder = new ProcessBuilder('/bin/bash', '-c', command)
        //             processBuilder.redirectOutput(ProcessBuilder.Redirect.to(outputFile))
        //             def process = processBuilder.start()
        //             process.waitFor()

        //             if (outputFile.exists()) {
        //             def outputContent = outputFile.text
        //             println "Command output:\n${outputContent}"
        //             } else {
        //             println "Output file not found."
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

        // stage('Build') {
        //     when {
        //         expression { return env.DEPLOY_OK.toBoolean() }
        //     }            
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
        //     when {
        //         expression { return env.DEPLOY_OK.toBoolean() }
        //     }
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
        //     when {
        //         expression { return env.DEPLOY_OK.toBoolean() }
        //     }
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
        //     when {
        //         expression { return env.DEPLOY_OK.toBoolean() }
        //     }                
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
        //     when {
        //         expression { return env.DEPLOY_OK.toBoolean() }
        //     }
        //     steps {
        //         sh "python3 -u service.py test ${ENVIRONMENT} ${BRANCH}"
        //     }
        // }
    
        // post {
        //     success {
        //         // send resource file to a file repository (S3, NFS etc...)
        //     }
        // }
    }   
}
