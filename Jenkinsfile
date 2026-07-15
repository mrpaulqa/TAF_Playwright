pipeline {
    // Указываем Jenkins запустить всю сборку внутри контейнера Playwright
    agent {
        docker {
            image 'mcr.microsoft.com/playwright/java:v1.49.0-noble'
        }
    }

    stages {
        stage('Run QA Tests') {
            steps {
                // Делаем скрипт Gradle исполняемым внутри контейнера
                sh 'chmod +x ./gradlew'

                // Запускаем тесты через Gradle Wrapper
                sh './gradlew test'
            }
        }
    }
}