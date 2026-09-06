---
id: algo-find-most-frequent-char
title: "Поиск самого частого символа в строке (с сохранением порядка первого вхождения)"
category: algorithms-tasks
difficulty: easy
frequency: high
confidence: 5
last_reviewed: 2026-09-06
tags:
  - live-coding
  - strings
  - hashmap
  - linkedhashmap
  - edge-cases
---

# ❓ Поиск самого частого символа в строке

### ⚡ Краткий ответ (TL;DR для повторения за 30–60 сек)
- Привести строку к нижнему регистру (`toLowerCase()`), отвалидировать на `null` и пустоту (`isBlank()`).
- Использовать `LinkedHashMap<Character, Integer>` или массив частот `int[256]`, пропуская пробелы.
- `LinkedHashMap` сохраняет **порядок первого добавления ключа**, поэтому при первом проходе и поиске максимума первый встреченный символ с максимальной частотой побеждает автоматически.
- Сложность: по времени $O(N)$, по памяти $O(1)$ (алфавит ограничен константой, не более 256 символов для ASCII или Unicode).

---

### 📋 Условие задачи
Дана строка `phrase`. Необходимо вернуть символ, который встречается чаще всего.
**Требования:**
1. Регистронезависимость (сравнение в lowercase).
2. Если строка `null` или пустая / состоит только из пробелов — бросать `IllegalArgumentException`.
3. Пробелы игнорируются.
4. Если несколько символов имеют одинаковую максимальную частоту — вернуть тот, который встретился **ближе к началу строки**.

**Пример:**
- *Вход*: `"The world is full of obvious things which nobody by any chance ever observes."`
- *Выход*: `'o'`

---

### 💻 Эталонное решение на Java

```java
package org.example;

import java.util.LinkedHashMap;
import java.util.Map;

public class FindMostFrequentOptimal {

    public static char findMostFrequent(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Входная строка не должна быть null или пустой");
        }

        String normalized = input.toLowerCase();
        // LinkedHashMap сохраняет порядок вставки ключей
        Map<Character, Integer> frequencies = new LinkedHashMap<>();

        for (int i = 0; i < normalized.length(); i++) {
            char ch = normalized.charAt(i);
            if (ch != ' ') {
                frequencies.put(ch, frequencies.getOrDefault(ch, 0) + 1);
            }
        }

        if (frequencies.isEmpty()) {
            throw new IllegalArgumentException("Строка не содержит значащих символов");
        }

        char mostFrequentChar = ' ';
        int maxCount = 0;

        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            // Строго больше (>), чтобы сохранить первый встреченный символ при равенстве частот
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentChar = entry.getKey();
            }
        }

        return mostFrequentChar;
    }
}
```

---

### ⚠️ Типичные ошибки и подводные камни
1. **Использование `HashMap` вместо `LinkedHashMap`**: обычный `HashMap` не гарантирует порядок итерации по `entrySet()`, поэтому при одинаковой частоте символов результат может оказаться случайным.
2. **Использование `>=` вместо `>`**: условие `entry.getValue() >= maxCount` перезапишет результат на последний встреченный символ, нарушив требование "вернуть тот, что ближе к началу".
3. **Необработанный `null`**: вызов `input.toLowerCase()` до проверки на `null` приведет к `NullPointerException`.
4. **Строка только из пробелов**: строка `"   "` после пропуска пробелов оставит пустую мапу. Нужно бросить `IllegalArgumentException`.

---

### 🔗 Исходный код в репозитории
- Исходный файл: [`src/main/java/org/example/FindMostFrequent.java`](../../../src/main/java/org/example/FindMostFrequent.java)
- Вариант с собеседования: [`src/main/java/org/example/FindMostFrequentFinishedResult.java`](../../../src/main/java/org/example/FindMostFrequentFinishedResult.java)
