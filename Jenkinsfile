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
        stage('Run Tests') {
            steps {
                cleanWs()
                checkout scm
                script {
                    def testTag = ''
                    sh 'chmod +x ./gradlew'
                    // 1. Если это запуск из Pull Request -> СТРОГО smoke
                    if (env.CHANGE_ID) {
                        echo "Авто-запуск для PR #${env.CHANGE_ID}: прогоняем только Smoke-тесты"
                        testTag = 'api'
                    }
                    else if (env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'main') {
                        echo "Изменения влиты в ${env.BRANCH_NAME}: запускаем полный регресс!"
                        sh "./gradlew clean test"
                    }
                    // 2. Если запуск по расписанию (Cron / Nightly) -> полный регресс
                    else if (BUILD_CAUSE == 'TIMERTRIGGER') {
                        echo "Ночной авто-запуск: прогоняем полный Regression"
                        testTag = 'ui'
                    }
                    // 3. Если запустили руками из UI -> берем выбор из параметров
                    else {
                        echo "Ручной запуск: выбран сьют ${params.TEST_SUITE}"
                        testTag = params.TEST_SUITE
                    }

                    // Передаем тег в Gradle
                    sh "./gradlew clean test"
                }
            }
        }
    }


    post {
        always {
            allure commandline: 'allure_cli',
            includeProperties: false,
            jdk: '',
            results: [[path: '**/allure-results']]

            mail(
                to: env.TEST_REPORT_EMAIL,
                subject: "Результаты тестов: Job '${env.JOB_NAME}' [Build #${env.BUILD_NUMBER}]",
                body: """
                     <html>
                     <body>
                          <h2>Статус сборки: ${currentBuild.currentResult}</h2>
                          <p>Автотесты завершены. Check Allure: <a href="${env.BUILD_URL}allure/">Allure Report</a></p>
                     </body>
                     </html>
                    """,
                mimeType: 'text/html'
            )
        }
    }
}