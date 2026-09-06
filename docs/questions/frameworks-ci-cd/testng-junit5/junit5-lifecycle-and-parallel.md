---
id: frameworks-junit5-lifecycle-parallel
title: "JUnit 5: Жизненный цикл тестов, параметризация (@ParameterizedTest) и параллельный запуск"
category: frameworks-ci-cd
difficulty: medium
frequency: high
confidence: 5
last_reviewed: 2026-09-06
tags:
  - junit5
  - testng
  - parallel-execution
  - parametrized-tests
  - extensions
---

# ❓ JUnit 5: Жизненный цикл, параметризация и параллельный запуск

### ⚡ Краткий ответ (TL;DR для повторения за 30–60 сек)
- **Архитектура JUnit 5**: JUnit Platform + JUnit Jupiter + JUnit Vintage (для обратной совместимости с JUnit 4).
- **Жизненный цикл**:
  - `@BeforeAll` (static по дефолту) ➔ `@BeforeEach` ➔ `@Test` ➔ `@AfterEach` ➔ `@AfterAll` (static).
  - По умолчанию на каждый тестовый метод создается **новый экземпляр класса** (`@TestInstance(PER_METHOD)`), предотвращая разделение состояния.
- **Параметризация**: `@ParameterizedTest` + `@ValueSource`, `@CsvSource`, `@CsvFileSource`, `@MethodSource`.
- **Параллелизм**: настраивается через `junit-platform.properties` (`junit.jupiter.execution.parallel.enabled=true`, режим `concurrent`). Для изоляции состояния драйверов используется `ThreadLocal`.

---

### 📖 Развернутый ответ

#### 1. Сравнение аннотаций JUnit 4 vs JUnit 5 vs TestNG
| Описание | JUnit 4 | JUnit 5 (Jupiter) | TestNG |
| :--- | :--- | :--- | :--- |
| **Тестовый метод** | `@Test` | `@Test` | `@Test` |
| **До всех тестов класса** | `@BeforeClass` (static) | `@BeforeAll` (static) | `@BeforeClass` |
| **Перед каждым тестом** | `@Before` | `@BeforeEach` | `@BeforeMethod` |
| **После каждого теста** | `@After` | `@AfterEach` | `@AfterMethod` |
| **После всех тестов класса**| `@AfterClass` (static) | `@AfterAll` (static) | `@AfterClass` |
| **Игнорирование теста** | `@Ignore` | `@Disabled` | `@Test(enabled=false)` |
| **Расширения / Перехватчики** | `@Rule`, `@ClassRule` | `@ExtendWith(Extension.class)` | `@Listeners` |

#### 2. Пример параметризованного теста (@MethodSource)
```java
@ParameterizedTest(name = "Тест с входными данными: {0}, ожидание: {1}")
@MethodSource("provideTestData")
void testValidStudents(String input, int expectedCount) {
    assertThat(Calculator.calculate(input)).isEqualTo(expectedCount);
}

private static Stream<Arguments> provideTestData() {
    return Stream.of(
        Arguments.of("student1", 5),
        Arguments.of("student2", 10)
    );
}
```

---

### 🔥 Каверзные вопросы на собеседовании

1. *Как обеспечить потокобезопасность WebDriver при параллельном прогоне тестов?*
   - **Ответ**: Оборачивать экземпляр WebDriver в `ThreadLocal<WebDriver>` или делегировать управление пулу в базовом классе / синглтоне со scoped фабрикой. В `@AfterEach` обязательно вызывать `driver.get().quit()` и `driver.remove()`, чтобы избежать утечки памяти.

2. *В чем разница между JUnit 5 Extensions и TestNG Listeners?*
   - JUnit 5 объединил все устаревшие механизмы (Runners, Rules) в единую концепцию **Extension Model** (`BeforeEachCallback`, `ParameterResolver`, `TestExecutionExceptionHandler`). TestNG использует множество специализированных интерфейсов (`ITestListener`, `ISuiteListener`, `IAnnotationTransformer`).
