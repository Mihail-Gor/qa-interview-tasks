---
date: 2026-09-07
company: "Fintech Enterprise"
position: "Senior Java QA Automation (Backend + Frontend)"
result: "Technical round passed"
overall_score: 4
interviewer_roles: "Lead SDET, Team Lead Backend"
duration_minutes: 75
---

# 🎙️ Дебрифинг собеседования: Fintech Enterprise (2026-09-07)

### 📋 Общие впечатления и формат
- **Формат**: Live Coding (25 мин) + Архитектура и Backend API (30 мин) + Java Core & Multithreading (20 мин).
- **Общая атмосфера**: Конструктивная, упор на надежность автотестов, работу с брокерами сообщений и многопоточность.
- **Позитивные моменты**:
  - Быстро и без ошибок решил практическую задачу на подсчет частоты символов с сохранением порядка вставки.
  - Подробно объяснил устройство REST Assured фильтров и логирование в Allure.

---

### 💬 Заданные вопросы и ход интервью

#### 1. Java Core & Multithreading
- **Вопрос 1**: *Как устроен `ConcurrentHashMap` в Java 8 и чем отличается от `Collections.synchronizedMap`?*
  - *Как ответил*: Рассказал про отсутствие сегментов в Java 8, использование `CAS` для пустых бакетов и `synchronized` на первый узел бакета.
  - *Оценка*: ✅ Отлично.
- **Вопрос 2**: *Что произойдет при возникновении коллизии в `HashMap`?*
  - *Как ответил*: Рассказал про переход связного списка в красно-черное дерево при `binCount >= 8` и `capacity >= 64`.
  - *Оценка*: ✅ Отлично.

#### 2. Backend & API Automation
- **Вопрос**: *Как надежно протестировать консьюмер Kafka без `Thread.sleep`?*
  - *Как ответил*: Предложил `Awaitility` с поллингом и проверку через Testcontainers / Embedded Kafka. Не до конца раскрыл валидацию dead-letter queue (DLQ).
  - *Оценка*: ⚠️ Частично (нужно глубже разобрать DLQ).

#### 3. Live Coding / Практика
- **Условие**: Найти самый частый символ в строке. При равенстве частот вернуть тот, который встретился раньше. Игнорировать пробелы и регистр.
- **Решение**: Использовал `LinkedHashMap` для подсчета частоты и сохранения порядка первого появления. Сложность: $O(N)$ по времени и $O(K)$ по памяти (где $K \le 256$).
- **Ссылка на код**: [`src/main/java/org/example/FindMostFrequent.java`](../../../src/main/java/org/example/FindMostFrequent.java)

---

### 🎯 Выявленные пробелы (Gaps & Action Items)
- [x] Разобрать паттерны обработки ошибок и Dead-Letter Queue (DLQ) в Kafka.
- [ ] Освежить в памяти различия между уровнями изоляции транзакций в PostgreSQL/MySQL (Repeatable Read vs Serializable).
