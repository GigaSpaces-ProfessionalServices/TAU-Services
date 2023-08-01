pipeline {
    
    agent any
    
    environment {
        GIT_URL = 'https://github.com/GigaSpaces-ProfessionalServices/TAU-Services.git'
        GIT_CREDS = ''
    }

    parameters {
        choice (
            choices: ['Create a new service', 'Deploy ', 'Create and deploy'], 
            description: 'Choose your preferred actions for a new service', 
            name: 'ACTION'
            )
        }
    
    stages {
        stage('Choose Fruit') {
            steps {
                script {
                    // Present choice for selecting the fruit
                    def userInput = input(
                        message: 'Choose your favorite fruit:',
                        parameters: [
                            choice(name: 'FRUIT_CHOICE', choices: 'Apple\nOrange\nBanana', description: 'Select your fruit')
                        ]
                    )

                    // Save the selected fruit in a variable
                    def selectedFruit = userInput['FRUIT_CHOICE']

                    // Determine additional parameters based on the selected fruit
                    def additionalParameters
                    switch (selectedFruit) {
                        case 'Apple':
                            additionalParameters = [
                                string(name: 'APPLE_TYPE', defaultValue: 'Fuji', description: 'Type of Apple')
                            ]
                            break
                        case 'Orange':
                            additionalParameters = [
                                string(name: 'ORANGE_SIZE', defaultValue: 'Medium', description: 'Size of Orange')
                            ]
                            break
                        case 'Banana':
                            additionalParameters = [
                                string(name: 'BANANA_RIPENESS', defaultValue: 'Slightly Ripe', description: 'Ripeness of Banana')
                            ]
                            break
                    }

                    // Present additional parameters based on the selected fruit
                    def additionalUserInput = input(
                        message: "Additional parameters for $selectedFruit:",
                        parameters: additionalParameters
                    )

                    // You can access the values of additional parameters as follows:
                    // def appleType = additionalUserInput['APPLE_TYPE']
                    // def orangeSize = additionalUserInput['ORANGE_SIZE']
                    // def bananaRipeness = additionalUserInput['BANANA_RIPENESS']
                }
            }
        }

        // Add more stages here to continue your pipeline based on user input
        // For example, you can use the selected values to trigger different build steps.
        // Or, you can use a declarative pipeline with parallel stages to perform actions concurrently based on choices.
    }
}
