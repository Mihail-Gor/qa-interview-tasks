---
id: backend-kafka-testing-strategies
title: "Тестирование Apache Kafka: стратегии, Awaitility, Testcontainers и Dead Letter Queue (DLQ)"
category: backend-automation
difficulty: hard
frequency: high
confidence: 4
last_reviewed: 2026-09-06
tags:
  - kafka
  - message-brokers
  - async-testing
  - awaitility
  - testcontainers
  - dlq
---

# ❓ Стратегии тестирования Apache Kafka в автотестах

### ⚡ Краткий ответ (TL;DR для повторения за 30–60 сек)
- **Проблема асинхронности**: Producer отправляет сообщение, но Consumer обрабатывает его с задержкой. Использовать `Thread.sleep()` в тестах — **антипаттерн** (приводит к нестабильности/flaky tests и замедлению CI).
- **Решение**: библиотека **Awaitility** (polling с экспоненциальным или фиксированным интервалом до выполнения условия или таймаута).
- **Окружение**: **Testcontainers** (`KafkaContainer`) для изоляции в CI/CD пайплайнах.
- **Сценарии тестирования**:
  1. *Happy Path*: сообщение попало в топик -> обработалось сервисом -> результат сохранился в БД.
  2. *Idempotency*: повторная отправка одного и того же события (`event_id`) не создает дубликатов в БД.
  3. *Error Handling / DLQ*: невалидное сообщение попадает в **Dead Letter Queue (DLQ)**, а основной топик не зависает.
  4. *Consumer Lag / Rebalancing*: поведение при падении одной из нод или ребалансировке партиций.

---

### 📖 Развернутый ответ

#### 1. Использование Awaitility для асинхронных проверок
```java
import static org.awaitility.Awaitility.await;
import java.time.Duration;

public class KafkaTestHelper {

    public static void waitForMessageInDb(OrderRepository repository, String orderId) {
        await()
            .atMost(Duration.ofSeconds(10))
            .pollInterval(Duration.ofMillis(200))
            .ignoreExceptions()
            .untilAsserted(() -> {
                Order order = repository.findById(orderId);
                assertThat(order).isNotNull();
                assertThat(order.getStatus()).isEqualTo(OrderStatus.PROCESSED);
            });
    }
}
```

#### 2. Проверка топика Dead Letter Queue (DLQ)
Если сообщение содержит невалидные данные (например, отрицательную цену), сервис не должен падать бесконечно, забивая офсеты.
1. Отправляем в топик `orders.input` невалидный payload.
2. Проверяем с помощью `KafkaConsumer`, что в топике `orders.DLQ` появилось сообщение с заголовками `x-exception-message` и исходным payload.
3. Проверяем, что офсет в основном топике сдвинулся (commit offset).

---

### 🔥 Каверзные вопросы на собеседовании

1. *Что такое Семантики доставки (Delivery Semantics) в Kafka?*
   - **At most once**: сообщение может потеряться, но не будет продублировано (commits offset до обработки).
   - **At least once**: сообщение никогда не потеряется, но возможны дубликаты (commits offset после обработки).
   - **Exactly once (EOS)**: транзакционный продюсер + консьюмер (идемпотентный продюсер `enable.idempotence=true`).

2. *Как изолировать тесты при параллельном запуске с одной Kafka?*
   - Использовать динамические имена топиков с префиксом/UUID на каждый тестовый класс или использовать уникальные `groupId` консьюмеров для каждого теста.
