pipeline {
    agent {
        docker {
            image 'mcr.microsoft.com/playwright/java:v1.49.0-noble'
            // Мы убрали привязку к группе :docker, оставив только root
            args '-u root'
        }
    }

    stages {
        stage('Checkout') {
            steps {
                cleanWs()
                checkout scm
            }
        }
        stage('Run QA Tests') {
            steps {
                sh 'chmod +x ./gradlew'
                sh './gradlew test'
            }
        }
    }
}