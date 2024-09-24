# Record Management API

## Описание

Этот проект представляет собой REST API сервис для управления записями в базе данных H2. Сервис поддерживает операции добавления, обновления, удаления и получения записей. Также реализованы тесты производительности для оценки работы сервиса.

## Технологии

- Java 17
- Spring Boot
- H2 Database
- JUnit для тестирования

## Установка и запуск

1. Склонируйте репозиторий:

   ```bash
   git clone https://github.com/krr006/Record-Management-API.git
   cd Record-Management-API

2. Соберите проект с помощью Maven:
    ```bash
   mvn clean package

3. Запустите приложение:
    ```bash
    mvn spring-boot:run

4. Для доступа к H2 консоли прейдите по адресу `http://localhost:8080/h2-console`.

   **Параметры подключения:**
    - JDBC URL: `jdbc:h2:mem:testdb`
    - User Name: `username`
    - Password: `password`

## Запуск тестов

1. Для выполнения тестов производительности выполните команду:
    ```bash
    mvn test

**Тесты производительности:**
- `testCreate100kRecords`: создает 100000 записей в базе данных и измеряет время выполнения.
testSelect1MillionRecordsWith100Connections: выполняет выборку 1000000 записей с использованием 100 потоков, собирая статистику по времени.

- `testSelect1MillionRecordsWith100Connections`: выполняет выборку 1000000 записей с использованием 100 потоков, собирая статистику по времени.


