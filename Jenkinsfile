pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }

    stages {

        stage('Clone') {
            steps {
                echo 'Cloning GitHub Repository ================================'
                git 'https://github.com/tghcastro/gullveig-service.git'
            }
        }

        stage('Build Services') {
            steps {
                echo 'All Services Build and Run Unit Tests ====================='
                sh './gradlew clean build'
                junit '**/build/test-results/test/TEST-*.xml'
            }
        }

        stage('Build Images (TODO)') {
            steps {
                echo 'Build Docker Images ======================================'
            }
        }

        stage('Functional Tests (TODO)') {
            steps {
                echo 'Run Functional Tests Against Test Environment ============'
            }
        }

        stage('Performance Tests (TODO)') {
            steps {
                echo 'Run Performance Tests Against Test Environment ==========='
            }
        }

        stage('Publish PROD Images (TODO)') {
            steps {
                echo 'Publish Prod images to Docker Hub ========================='
            }
        }
    }
}