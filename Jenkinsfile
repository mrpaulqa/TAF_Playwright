pipeline {
    agent {
        docker {
            // Используем официальный образ Playwright с Java и всем окружением
            image 'mcr.microsoft.com/playwright/java:v1.49.0-noble'
            args '-u root'
        }
    }

    // Все секреты из Jenkins Credentials
    environment {
        TEST_REPORT_EMAIL = credentials('TEST_REPORT_EMAIL')
        THINKING_EMAIL    = credentials('THINKING_EMAIL')
        THINKING_PASSWORD = credentials('THINKING_PASSWORD')
    }

    triggers {
        cron('H 2 * * 1-5')
    }

    options {
        timeout(time: 1, unit: 'HOURS')
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    parameters {
        choice(name: 'TEST_SUITE', choices: ['ui', 'api', 'db', 'all'], description: 'Какой набор тестов запустить (при ручном запуске)')
    }

    stages {
        stage('Run Automated Tests') {
            steps {
                script {
                    def testTag = ''

                    // 1. Если это запуск из Pull/Merge Request
                    if (env.CHANGE_ID) {
                        echo "Авто-запуск для PR #${env.CHANGE_ID}: прогоняем только API/Smoke тесты"
                        testTag = 'api'
                    }
                    // 2. Если запуск в ветке master/main (после мерджа)
                    else if (env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'main') {
                        echo "Изменения влиты в ${env.BRANCH_NAME}: прогоняем полный регресс!"
                        testTag = 'all'
                    }
                    // 3. Если запуск по расписанию (Cron)
                    else if (currentBuild.buildCauses.toString().contains('TimerTrigger')) {
                        echo "Ночной авто-запуск по расписанию: прогоняем UI тесты"
                        testTag = 'ui'
                    }
                    // 4. Ручной запуск из интерфейса Jenkins
                    else {
                        echo "Ручной запуск: выбран сьют ${params.TEST_SUITE}"
                        testTag = params.TEST_SUITE
                    }

                    // Установка Node.js, npm и Bruno CLI в Docker-контейнер
                    sh '''
                   # 1. Подключаем репозиторий Node.js 20.x
                   apt-get update && apt-get install -y curl
                   curl -fsSL https://deb.nodesource.com/setup_20.x | bash -

                    # 2. Устанавливаем свежую Node.js (вместе с npx/npm) и Bruno CLI
                   apt-get install -y nodejs
                   npm install -g @usebruno/cli
                '''

                    // Права на исполнение gradlew внутри Linux Docker-контейнера
                    sh 'chmod +x ./gradlew'

                    // Запуск тестов
                    sh "./gradlew clean test -Dtag=${testTag} --no-daemon -Dapi.thinkingEmail='${THINKING_EMAIL}' -Dapi.thinkingPassword='${THINKING_PASSWORD}'"
                }
            }
        }
    }

    post {
        always {
            // Генерация Allure Report
            allure commandline: 'allure_cli',
            includeProperties: false,
            jdk: '',
            results: [[path: 'build/allure-results']]

            // Отправка Email
            mail(
                to: env.TEST_REPORT_EMAIL,
                subject: "Результаты тестов: Job '${env.JOB_NAME}' [Build #${env.BUILD_NUMBER}]",
                body: """
                     <html>
                     <body>
                          <h2>Статус сборки: ${currentBuild.currentResult}</h2>
                          <p>Автотесты завершены. Посмотреть отчет: <a href="${env.BUILD_URL}allure/">Allure Report</a></p>
                     </body>
                     </html>
                    """,
                mimeType: 'text/html'
            )

            // Очистка рабочей директории
            cleanWs()
        }
    }
}