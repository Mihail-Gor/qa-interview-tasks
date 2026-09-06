---
id: architecture-design-framework-layers
title: "Архитектура современного enterprise-фреймворка автотестирования (Clean Architecture)"
category: architecture-design
difficulty: hard
frequency: high
confidence: 5
last_reviewed: 2026-09-06
tags:
  - architecture
  - test-framework
  - clean-architecture
  - design-patterns
  - layered
---

# ❓ Архитектура современного тест-фреймворка

### ⚡ Краткий ответ (TL;DR для повторения за 30–60 сек)
- **Слоистая архитектура (Layered Architecture)**:
  1. **Test Layer**: только бизнес-сценарии и ассерты (`assertThat(...)`), никакого низкоуровневого HTTP или WebDriver кода.
  2. **Business / Step Layer**: композитные действия, бизнес-логика (например, `UserSteps.registerAndLogin()`).
  3. **Page Object / API Client Layer**: инкапсуляция страниц (Selenide/Selenium) и REST клиентов (Retrofit / REST Assured).
  4. **Core / Infrastructure Layer**: конфигурация (Owner), драйверы, клиенты к БД (jOOQ/JDBC) и брокерам (Kafka), кастомные Allure-листенеры.
- **Паттерны проектирования**:
  - *Page Object & Page Element / Component Object* (UI).
  - *Builder / Factory / Object Mother / Data Generator (Java Faker)* (Тестовые данные).
  - *Singleton / ThreadLocal Driver Factory* (Управление браузером/клиентами).
  - *Adapter / Wrapper* (Для библиотек и внешних сервисов).

---

### 📖 Развернутый ответ

```text
src/
├── main/java/com/company/framework/
│   ├── config/              # Owner / TypeSafe Config (url, creds, timeouts)
│   ├── drivers/             # WebDriverFactory, ThreadLocal management
│   ├── api/                 # RestAssured clients, DTOs, Specs, Filters
│   ├── db/                  # SQL / JDBC / Hibernate connection pool
│   ├── kafka/               # Kafka producers / consumers, Awaitility helpers
│   └── ui/
│       ├── pages/           # Page Objects
│       └── components/      # Reusable UI widgets (Header, Modal, Table)
│
└── test/java/com/company/tests/
    ├── base/                # BaseTest, JUnit 5 Extensions, Test lifecycle
    ├── api/                 # API regression suites
    ├── ui/                  # UI E2E test suites
    └── data/                # Data Providers, Test Data Builders (Faker)
```

---

### 🔥 Каверзные вопросы на собеседовании

1. *Как бороться с Flaky (нестабильными) тестами в CI?*
   - **Ответ**:
     1. Запретить `Thread.sleep()`, перейти на Smart Waits (Selenide / Awaitility).
     2. Изолировать тестовые данные (генерировать уникальные сущности под каждый тест с префиксом/UUID вместо фиксированных).
     3. Изолировать браузерные сессии (`ThreadLocal`, отдельный инстанс драйвера на каждый параллельный поток).
     4. Настроить Test Retry Analyzer (например, перезапуск упавшего теста 1 раз) и логировать артефакты (скриншоты, видео, сетевые логи HAR).

2. *Как устроено управление конфигурациями (окружениями dev/stage/prod) в Java?*
   - **Ответ**: Использование библиотеки **Owner** (`org.aeonbits.owner.Config`) или Spring Boot Profile (`application-stage.yaml`). Значения считываются из системных свойств JVM (`-Denv=stage`) и fallback-файлов свойств `.properties`.
