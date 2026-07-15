pipeline {
    agent {
        docker {
            image 'mcr.microsoft.com/playwright/java:v1.49.0-noble'
        }
    }

    stages {
        stage('Clean Workspace') {
            steps {
                // Очищаем папку перед началом, чтобы не было конфликтов Git
                cleanWs()
            }
        }
        stage('Checkout') {
            steps {
                // Скачиваем свежий код из Git
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