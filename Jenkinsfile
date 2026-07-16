pipeline {
    // Указываем Jenkins запустить тесты внутри официального контейнера Playwright
    agent {
        docker {
            image 'mcr.microsoft.com/playwright/java:v1.49.0-noble'
            // Ключевой флаг, который решает проблему с правами в Docker Desktop на Windows/Mac
            args '-u root:docker'
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
                // Так как образ на Linux, запускаем через sh без лишних проверок ОС
                sh 'chmod +x ./gradlew'
                sh './gradlew test'
            }
        }
    }
}