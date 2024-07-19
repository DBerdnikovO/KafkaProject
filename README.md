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
- Postgres DB

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

## Конфигурация окружающей среды
Создайте файл `env.properties` в каждом проекте и добавьте конфигурацию:

```properties
SERVER_PORT=
BOOTSTRAP_SERVERS=

DATABASE_URL=
DATABASE_PASSWORD=
DATABASE_USER=
```

## Конфигурация Kafka Producer

### `ProducerConfig.BOOTSTRAP_SERVERS_CONFIG`
**Описание:** Список адресов Kafka брокеров.  
**Значение:** `bootstrapServers` — переменная, значение которой считывается из файла конфигурации.

### `ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG`
**Описание:** Класс, используемый для сериализации ключей сообщений.  
**Значение:** `StringSerializer.class` — стандартный сериализатор для строк.

### `ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG`
**Описание:** Класс, используемый для сериализации значений сообщений.  
**Значение:** `StringSerializer.class` — стандартный сериализатор для строк.

### `ProducerConfig.BUFFER_MEMORY_CONFIG`
**Описание:** Количество памяти, выделенной для буфера сообщений.  
**Значение:** `bufferMemory` — переменная, значение которой считывается из файла конфигурации.  
**Пример значения:** `33554432` (32 МБ)

### `ProducerConfig.LINGER_MS_CONFIG`
**Описание:** Максимальное время ожидания перед отправкой сообщения, чтобы собрать больше сообщений в один запрос.  
**Значение:** `lingerMs` — переменная, значение которой считывается из файла конфигурации.  
**Пример значения:** `10` (10 миллисекунд)

### `ProducerConfig.RETRIES_CONFIG`
**Описание:** Количество попыток повторной отправки сообщения в случае неудачи.  
**Значение:** `retries` — переменная, значение которой считывается из файла конфигурации.  
**Пример значения:** `3` (3 попытки)

### `ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG`
**Описание:** Время ожидания ответа от сервера.  
**Значение:** `requestTimeoutMs` — переменная, значение которой считывается из файла конфигурации.  
**Пример значения:** `15000` (15 секунд)

## Конфигурация Kafka Consumer

### Получение сообщений
**Описание:** Сервис слушает сообщения из основного Kafka топика с названием `metrics-topic`.

### Обработка сообщений
- **Успешная обработка:** Если сообщение успешно обрабатывается, оно сохраняется в системе и записывается в лог.
- **Ошибка обработки:** При ошибке (например, при сохранении данных или обработке JSON) сообщение отправляется в Dead Letter Topic (DLT) для последующего анализа.

### Повторные попытки
- **Количество попыток:** Максимальное количество попыток повторной обработки сообщения — 4.
- **Задержка:** Задержка между попытками увеличивается в два раза с каждой новой попыткой, начиная с 1000 миллисекунд.

### Dead Letter Topic (DLT)
- **Отправка сообщений:** Сообщения, которые не удалось обработать после всех попыток повторной обработки, отправляются в Dead Letter Topic (`metrics-topic-dlt`).
- **Запись в лог:** Информация о сообщениях, отправленных в DLT, записывается в лог для дальнейшего анализа.

## Документация
Документация достпуна по ссылке
[http://localhost:8081/swagger-ui/index.html#/](URL) , где на месте 8081 указывайте порт, на котором приложение запущено

Metric Producer создан для того, чтобы отправлять данные о системе и ошибках в топик metrics-topic
Metric Consumer создан для того, чтобы принимать и анализировать данные, высланные в metric-topic

Каждые 5 секунд metric producer отправляет данные в metric consumer
