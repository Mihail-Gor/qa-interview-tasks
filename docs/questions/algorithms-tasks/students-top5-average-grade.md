---
id: algo-students-top5-average-grade
title: "Топ-5 студентов по среднему баллу (Сортировка объектов DTO, Stream API, Тест-дизайн)"
category: algorithms-tasks
difficulty: easy
frequency: high
confidence: 5
last_reviewed: 2026-09-06
tags:
  - live-coding
  - streams
  - comparator
  - sorting
  - test-design
---

# ❓ Топ-5 студентов по среднему баллу (Сортировка DTO и Тестирование)

### ⚡ Краткий ответ (TL;DR для повторения за 30–60 сек)
- Использовать `Stream API` с `Comparator.comparingDouble(...).reversed()`, `limit(5)` и `.toList()`.
- При парсинге числовых значений из `String` (`avgGrade`) обязательно учитывать формат чисел (`Double.parseDouble`), `NumberFormatException`, `null` и локаль (точка vs запятая).
- Для QA важно не только написать алгоритм, но и перечислить тест-кейсы: пустой список, список < 5 элементов, одинаковые оценки, граничные значения (0.0, 5.0), некорректный формат строки.

---

### 📋 Условие задачи
Дан список объектов `StudentDto` с полями:
- `firstName` (String)
- `lastName` (String)
- `avgGrade` (String)
- `age` (String)

**Требования:**
1. Отсортировать студентов по `avgGrade` в порядке убывания.
2. Вернуть первых 5 студентов из отсортированного списка (`List<StudentDto>`).
3. Предложить тест-дизайн для проверки этой логики.

---

### 💻 Эталонное решение на Java (Stream API)

```java
package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class StudentsAndTestsOptimal {

    public record StudentDto(String firstName, String lastName, String avgGrade, String age) {}

    public static List<StudentDto> getTop5Students(List<StudentDto> students) {
        if (students == null) {
            throw new IllegalArgumentException("Список студентов не может быть null");
        }

        return students.stream()
                .filter(Objects::nonNull)
                .filter(s -> s.avgGrade() != null && !s.avgGrade().isBlank())
                .sorted(Comparator.comparingDouble(
                        (StudentDto s) -> Double.parseDouble(s.avgGrade().trim())
                ).reversed())
                .limit(5)
                .toList();
    }
}
```

---

### 🧪 Тест-дизайн задачи (Вопрос на собеседовании для QA Automation)
На собеседовании часто просят: *"Как бы вы протестировали эту функцию?"*

1. **Позитивные сценарии**:
   - Стандартный список из 7+ студентов с разными оценками -> возвращаются ровно 5 лучших в правильном порядке.
   - Список ровно из 5 студентов -> возвращаются все 5 отсортированных.
   - Список из 1–4 студентов -> возвращаются все имеющиеся студенты (без `IndexOutOfBoundsException`).
   - Студенты с одинаковыми средними оценками -> сохранение стабильности сортировки.
2. **Граничные значения оценок**:
   - Минимальный балл (`0.0` / `1.0`), максимальный балл (`5.0` / `100.0`).
   - Числа с плавающей точкой разной точности (`4.85` vs `4.851`).
3. **Негативные сценарии и валидация данных**:
   - Входной список `null` -> `IllegalArgumentException`.
   - Пустой список `List.of()` -> пустой результат `[]`.
   - Студент со значением `avgGrade = null` или `"N/A"` / `"abc"` -> фильтрация или контролируемая ошибка.
   - Строки с пробелами по краям `" 4.8 "` -> успешный trim и парсинг.

---

### 🔗 Исходный код в репозитории
- Исходный файл: [`src/main/java/org/example/StudentsAndTests.java`](../../../src/main/java/org/example/StudentsAndTests.java)
