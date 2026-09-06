---
id: algo-occupied-seat-rows-distance
title: "Выбор места в кинотеатре с максимальной дистанцией до соседей (Maximize Distance to Closest Person)"
category: algorithms-tasks
difficulty: medium
frequency: medium
confidence: 4
last_reviewed: 2026-09-06
tags:
  - live-coding
  - arrays
  - math
  - greedy
  - leetcode-849
---

# ❓ Выбор места в ряду с максимальной дистанцией до ближайшего человека

### ⚡ Краткий ответ (TL;DR для повторения за 30–60 сек)
- Задача аналогична **LeetCode 849 (Maximize Distance to Closest Person)**.
- Разделяем анализ на три случая:
  1. **Свободные места в начале ряда** (до первой `1`): дистанция $D = \text{индекс первой 1}$. Оптимальное место — индекс `0`.
  2. **Свободные места между двумя единицами**: дистанция $D = (\text{right} - \text{left}) / 2$. Оптимальное место — середина $(\text{left} + \text{right}) / 2$.
  3. **Свободные места в конце ряда** (после последней `1`): дистанция $D = (N - 1) - \text{индекс последней 1}$. Оптимальное место — индекс $N - 1$.
- Сложность: по времени $O(N)$ (один проход по массиву), по памяти $O(1)$.

---

### 📋 Условие задачи
В ряду кинотеатра места представлены массивом целых чисел:
- `1` — место занято,
- `0` — место свободно.

Нужно посадить человека так, чтобы расстояние до ближайшего соседа было максимально возможным. Вернуть индекс места.
*Гарантируется, что в ряду есть минимум одно свободное и одно занятое место.*

---

### 💻 Эталонное и лаконичное решение на Java ($O(N)$ по времени, $O(1)$ по памяти)

```java
package org.example;

import java.util.List;

public class OccupiedSeatRowsOptimal {

    public static int chooseSeatIndex(List<Integer> seats) {
        int n = seats.size();
        int maxDistance = 0;
        int bestIndex = -1;

        int prevOccupied = -1;

        for (int i = 0; i < n; i++) {
            if (seats.get(i) == 1) {
                if (prevOccupied == -1) {
                    // Случай 1: с самого начала ряда до первой 1
                    int dist = i;
                    if (dist > maxDistance) {
                        maxDistance = dist;
                        bestIndex = 0;
                    }
                } else {
                    // Случай 2: между двумя занятыми местами
                    int dist = (i - prevOccupied) / 2;
                    if (dist > maxDistance) {
                        maxDistance = dist;
                        bestIndex = prevOccupied + dist;
                    }
                }
                prevOccupied = i;
            }
        }

        // Случай 3: от последней 1 до конца ряда
        if (prevOccupied != n - 1) {
            int dist = (n - 1) - prevOccupied;
            if (dist > maxDistance) {
                maxDistance = dist;
                bestIndex = n - 1;
            }
        }

        return bestIndex;
    }
}
```

---

### ⚠️ Подводные камни и граничные тесты для QA
1. **Единица в самом начале / самом конце**: `[1, 0, 0, 0]` ➔ лучше сесть на индекс 3 (дистанция 3).
2. **Единица в самом конце**: `[0, 0, 0, 1]` ➔ лучше сесть на индекс 0 (дистанция 3).
3. **Четное количество нулей между единицами**: `[1, 0, 0, 0, 0, 1]` (4 нуля) ➔ дистанция $\lfloor 4 / 2 \rfloor = 2$, индекс 2 или 3.
4. **Нечетное количество нулей между единицами**: `[1, 0, 0, 0, 1]` (3 нуля) ➔ дистанция 2, индекс 2.

---

### 🔗 Исходный код в репозитории
- Исходный файл: [`src/main/java/org/example/OccupiedSeatRows.java`](../../../src/main/java/org/example/OccupiedSeatRows.java)
