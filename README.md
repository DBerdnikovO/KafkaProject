# Kafka проект для отправки, приема, анализа метрик

## Обзор
Этот проект представляет собой Spring Boot приложение,которое включает в себя конфигурацию для Kafka консюмера и продюсера, сервисы для создания, приема и анализа данных и документацию OpenAPI.
## Содержание
- [Требования](#требования)
- [Установка](#установка)
- [Конфигурация](#конфигурация)
- [Документация](#документация)

## Требования
- Java 11 или выше
- Apache Kafka
- Spring Boot

## Установка
1. Клонируйте репозиторий:
    ```sh
    git clone https://github.com/DBerdnikovO/KafkaProject.git
    ```
2. Перейдите в директорию проекта для producer:
    ```sh
    cd producer/
    ```
3. Соберите проект:
    ```sh
    mvn clean install
    ```
4. Перейдите в директорию проекта для producer:
    ```sh
    cd consumer/
    ```
5. Соберите проект:
    ```sh
    mvn clean install
    ```
5. Запустите docker:
    ```sh
    docker-compose up
    ```

## Конфигурация
Создайте файл `env.properties` в каждом проекте и добавьте конфигурацию:

```properties
SERVER_PORT=
BOOTSTRAP_SERVERS=

DATABASE_URL=
DATABASE_PASSWORD=
DATABASE_USER=
```

## Документация
Документация достпуна по ссылке
[http://localhost:8081/swagger-ui/index.html#/](URL) , где на месте 8081 указывайте порт, на котором приложение запущено

Metric Producer создан для того, чтобы отправлять данные о системе и ошибках в топик metrics-topic
Metric Consumer создан для того, чтобы принимать и анализировать данные, высланные в metric-topic

Каждые 5 секунд metric producer отправляет данные в metric consumer
