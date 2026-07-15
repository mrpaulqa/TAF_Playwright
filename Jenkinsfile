pipeline {
    agent any // Запускать на любом доступном агенте

    stages {
        stage('Checkout') {
            steps {
                cleanWs()
                checkout scm
            }
        }
        stage('Run Tests') {
            steps {
                // Если ты на Windows, используй bat, если на macOS/Linux — sh
                // Jenkins сам поймет твою ОС
                script {
                    if (isUnix()) {
                        sh 'chmod +x ./gradlew'
                        sh './gradlew test'
                    } else {
                        bat 'gradlew.bat test'
                    }
                }
            }
        }
    }
}