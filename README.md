# AI_PW — Test Automation Framework

A Java 17 + Gradle test automation framework covering **UI**, **API**, and **DB**
testing behind a **Cucumber/Gherkin** BDD layer, with **Allure** reporting.

| Concern        | Technology                          |
|----------------|-------------------------------------|
| Build tool     | Gradle (Groovy DSL, `build.gradle`) |
| UI testing     | Playwright for Java (Chromium)      |
| API testing    | REST Assured                        |
| DB testing     | JDBC (MySQL / embedded H2)          |
| BDD layer      | Cucumber + Gherkin feature files    |
| Reporting      | Allure Report                       |
| Infrastructure | Docker Compose (MySQL container)    |

## Project structure

```
.
├── build.gradle                 # dependencies + test/cucumber tasks
├── settings.gradle              # project name
├── docker-compose.yml           # MySQL service for DB tests
├── README.md
└── src/test
    ├── java/com/bit
    │   ├── CucumberRunner.java   # JUnit Platform Suite + Allure plugin
    │   ├── ui/                   # BasePage (Playwright Page) + page objects
    │   ├── api/                  # BaseApi (REST Assured)
    │   ├── db/                   # BaseDB (JDBC/MySQL)
    │   ├── steps/                # Cucumber step definitions
    │   ├── hooks/                # Playwright & DB @Before/@After hooks
    │   └── utils/                # ConfigReader, PlaywrightFactory
    └── resources
        ├── features/             # ui.feature, api.feature, db.feature
        ├── config.properties     # base URLs + DB connection config
        ├── allure.properties     # Allure results directory
        └── junit-platform.properties
```

## Prerequisites

- JDK 17+ (the build targets Java 17 bytecode)
- Docker + Docker Compose (only needed to run DB tests against MySQL)
- [Allure CLI](https://allurereport.org/docs/install/) (only needed to view the report)

## Setup & run

### 1. Run the tests

The default configuration uses an embedded **H2** database (MySQL-compatibility
mode), so the full suite runs out of the box with **no external services**:

```bash
./gradlew test
```

You can also run via the Cucumber CLI task:

```bash
./gradlew cucumber
```

> The first UI run downloads the Chromium browser automatically. To pre-fetch it:
> `./gradlew installPlaywright`

### 2. Run DB tests against the MySQL container

Start MySQL with Docker Compose:

```bash
docker-compose up -d
```

Then point the DB layer at MySQL via system properties (credentials match
`docker-compose.yml`):

```bash
./gradlew test \
  -Ddb.url=jdbc:mysql://localhost:3306/test_db \
  -Ddb.driver=com.mysql.cj.jdbc.Driver \
  -Ddb.user=test_user \
  -Ddb.password=test_pass
```

The same values can be supplied as environment variables (`DB_URL`, `DB_DRIVER`,
`DB_USER`, `DB_PASSWORD`). Stop the container with `docker-compose down`.

### 3. View the Allure report

Test execution writes results to `build/allure-results`. To open the report:

```bash
allure serve build/allure-results
```

## Configuration

All settings live in `src/test/resources/config.properties` and can be overridden
per run with a `-D` system property or an `UPPER_SNAKE_CASE` environment variable
(e.g. `db.url` → `DB_URL`):

| Key          | Default                                     | Purpose                |
|--------------|---------------------------------------------|------------------------|
| `ui.baseUrl` | `https://playwright.dev`                    | UI under test          |
| `ui.headless`| `true`                                      | Playwright headless    |
| `api.baseUrl`| `https://jsonplaceholder.typicode.com`      | API base URI           |
| `db.url`     | embedded H2 (MySQL mode)                    | JDBC connection URL    |
| `db.driver`  | `org.h2.Driver`                             | JDBC driver class      |
| `db.user`    | `sa`                                        | DB user                |
| `db.password`| _(empty)_                                   | DB password            |

## Sample scenarios

- `features/ui.feature`  — opens the UI home page and asserts its title (`@ui`)
- `features/api.feature` — GETs a resource and asserts status + body (`@api`)
- `features/db.feature`  — inserts and reads back a row via JDBC (`@db`)
