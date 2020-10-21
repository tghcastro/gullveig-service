pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }

    stages {

        stage('Clone') {
            steps {
                git 'https://github.com/tghcastro/gullveig-service.git'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
                junit '**/build/test-results/test/TEST-*.xml'
            }
        }
    }
}