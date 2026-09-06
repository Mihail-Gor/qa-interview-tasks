# 🎯 База знаний для подготовки к собеседованиям (Java QA Automation)

Добро пожаловать в структурированную базу знаний для подготовки к собеседованиям на позицию **Java QA Automation Engineer (Backend + Frontend)**.

---

## 🧭 Как устроена база знаний

- `questions/` — каталог вопросов и ответов, разбитый по техническим направлениям. Каждый вопрос оформлен в виде карточки с метаданными (YAML frontmatter), краткой выжимкой (**TL;DR** — *Too Long; Didn't Read* / суть ответа за 30 секунд), развернутым объяснением, частыми каверзными вопросами и ссылками на практические задачи.
- `interviews/` — архив разборов прошедших собеседований (дебрифинги) и сводный бэклог пробелов (`gaps-backlog.md`).
- `templates/` — стандартизированные шаблоны для добавления новых вопросов и фиксации результатов собеседований.
- `src/main/java/org/example/` — исходный код практических задач с собеседований (Live Coding).

---

## 🗺️ Карта разделов (Map of Content)

### 1. ☕ [Java Core & Multithreading](questions/java-core/)
- [Как устроен HashMap внутри в Java 8+](questions/java-core/collections-hashmap-internal.md) *(Коллекции, коллизии, красно-черные деревья)*
- [Внутреннее устройство ConcurrentHashMap](questions/java-core/multithreading-concurrenthashmap.md) *(Многопоточность, CAS, сегменты vs блокировка бакетов)*
- [Ключевые слова volatile, synchronized и Java Memory Model](questions/java-core/multithreading-volatile-synchronized.md) *(Happens-Before, атомарность, видимость)*
- [Память JVM (Heap, Metaspace, Stack) и работа Garbage Collector](questions/java-core/jvm-memory-model-gc.md) *(Утечки памяти, G1, ZGC, OutOfMemoryError)*

### 2. 🧩 [Алгоритмы и Live Coding задачи](questions/algorithms-tasks/)
- [Обзор практических задач репозитория](questions/algorithms-tasks/tasks-overview.md)
- [Поиск самого частого символа в строке](questions/algorithms-tasks/find-most-frequent-char.md) ➔ [`FindMostFrequent.java`](../src/main/java/org/example/FindMostFrequent.java)
- [Выбор места в ряду с максимальной дистанцией до соседей](questions/algorithms-tasks/occupied-seat-rows-distance.md) ➔ [`OccupiedSeatRows.java`](../src/main/java/org/example/OccupiedSeatRows.java)
- [Топ-5 студентов по среднему баллу (Сортировка / Stream API)](questions/algorithms-tasks/students-top5-average-grade.md) ➔ [`StudentsAndTests.java`](../src/main/java/org/example/StudentsAndTests.java)

### 3. ⚙️ [Backend Automation & API](questions/backend-automation/)
- **HTTP / REST API**:
  - [Принципы REST, HTTP методы, коды ответов и идемпотентность](questions/backend-automation/http-rest-api/rest-principles-http-codes.md)
- **REST Assured**:
  - [Кастомные фильтры, Request/Response Specs и логирование](questions/backend-automation/rest-assured/filters-specs-logging.md)
- **Databases & SQL**:
  - [SQL JOINs, индексы, уровни изоляции транзакций и ACID](questions/backend-automation/databases-sql/sql-joins-indexes-transactions.md)
- **Message Brokers (Kafka / RabbitMQ)**:
  - [Стратегии тестирования асинхронных событий в Kafka и Awaitility](questions/backend-automation/message-brokers/kafka-testing-strategies.md)

### 4. 🌐 [Frontend Automation (UI)](questions/frontend-automation/)
- **Web Basics**:
  - [CSS селекторы vs XPath, работа с Shadow DOM и iframe](questions/frontend-automation/web-basics/css-vs-xpath-selectors.md)
- **Selenium / Selenide**:
  - [Явные/неявные ожидания и борьба со StaleElementReferenceException](questions/frontend-automation/selenium-selenide/waits-and-stale-element.md)

### 5. 🏗️ [Фреймворки, CI/CD и Архитектура](questions/frameworks-ci-cd/)
- **JUnit 5 / TestNG**:
  - [Жизненный цикл тестов JUnit 5, параллельный запуск и Data Provider](questions/frameworks-ci-cd/testng-junit5/junit5-lifecycle-and-parallel.md)
- **Architecture & Design**:
  - [Архитектура enterprise-фреймворка автотестирования (Clean Architecture / Layered)](questions/architecture-design/automation-framework-architecture.md)

### 6. 🧪 [Теория тестирования](questions/testing-theory/)
- [Техники тест-дизайна и их применение в автоматизации](questions/testing-theory/test-design-techniques.md)

---

## 🔄 Процесс: как работать с базой

### Быстрое повторение перед интервью (30–60 минут)
1. Откройте нужную тему и читайте секции **Краткая выжимка (TL;DR — суть ответа)**.
2. Проверьте себя по блоку **Каверзные вопросы на собеседовании**.
3. Загляните в [`interviews/gaps-backlog.md`](interviews/gaps-backlog.md) и повторите темы с приоритетом `High`.

### Действия после собеседования (Ретроспектива)
1. Скопируйте шаблон [`templates/interview-debrief-template.md`](templates/interview-debrief-template.md) в папку `interviews/2026/`.
2. Зафиксируйте вопросы, которые вызвали затруднения, и фидбек интервьюеров.
3. Добавьте нераскрытые темы в [`interviews/gaps-backlog.md`](interviews/gaps-backlog.md).
4. Если всплыл новый вопрос — создайте карточку по шаблону [`templates/question-template.md`](templates/question-template.md).
