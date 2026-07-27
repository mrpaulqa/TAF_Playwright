pipeline {
    agent {
        docker {
            image 'mcr.microsoft.com/playwright/java:v1.49.0-noble'
            // Мы убрали привязку к группе :docker, оставив только root
            args '-u root'
        }
    }
    environment {
        TEST_REPORT_EMAIL = credentials('TEST_REPORT_EMAIL')
    }
    triggers {
        cron('H 2 * * 1-5')
    }
    // Храним настройки времени и параметров в одном месте
    options {
        timeout(time: 1, unit: 'HOURS') // Защита от бесконечного висания тестов
        timestamps()                     // Добавляет время к каждой строчке лога
        buildDiscarder(logRotator(numToKeepStr: '10')) // Храним только последние 10 сборок (экономим диск)
    }
    parameters {
        // Позволяет при ручном запуске выбрать, что именно гоним
        choice(name: 'TEST_SUITE', choices: ['ui', 'api', 'db','all'], description: 'Какой набор тестов запустить')
    }
    stages {
        // 1. Быстрый Smoke-прогон (работает ВСЕГДА и везде: в PR и в master)
        stage('Smoke Tests') {
            steps {
                sh 'chmod +x ./gradlew'
                sh './gradlew clean smokeTest'
            }
        }

        // 2. Полный UI прогон (работает ТОЛЬКО в ветке master после мерджа)
        stage('UI Tests') {
            when {
                branch 'master' // Запустится только когда код уже влит в master
            }
            steps {
                sh './gradlew clean test'
            }
        }
    }

    post {
        always {
            allure commandline: 'allure_cli',
            includeProperties: false,
            jdk: '',
            results: [[path: 'build/allure-results']]

            mail(
                to: env.TEST_REPORT_EMAIL,
                subject: "[Docker] Результаты тестов: Job '${env.JOB_NAME}' [Build #${env.BUILD_NUMBER}]",
                body: """
                     <html>
                     <body>
                          <h2>[Docker]Статус сборки: ${currentBuild.currentResult}</h2>
                          <p>Автотесты завершены. Check Allure: <a href="${env.BUILD_URL}allure/">Allure Report</a></p>
                     </body>
                     </html>
                    """,
                mimeType: 'text/html'
            )
            cleanWs()
        }
    }
}
