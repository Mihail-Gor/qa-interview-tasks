---
id: live-coding-tasks-overview
title: "Обзор практических задач на собеседованиях по Java QA Automation"
category: algorithms-tasks
difficulty: medium
frequency: high
confidence: 5
last_reviewed: 2026-09-06
tags:
  - live-coding
  - algorithms
  - strings
  - collections
  - streams
---

# 🧩 Обзор практических задач репозитория

Секция Live Coding на собеседованиях для QA Automation проверяет:
1. Знание стандартных структур данных Java (`HashMap`, `LinkedHashMap`, `ArrayList`, `Set`).
2. Умение писать чистый, читаемый код с обработкой граничных условий (`null`, пустая строка, пробелы).
3. Умение оценивать временную сложность $O(N)$ и пространственную сложность $O(M)$.
4. Владение современными конструкциями Java (Stream API, лямбды, `Comparator`, `Optional`).
5. Навык написания юнит-тестов для проверки своего кода.

---

### 📂 Задачи в данном проекте

| Задача | Файл с решением | Карточка разбора | Ключевые концепции | Сложность |
| :--- | :--- | :--- | :--- | :--- |
| **Find Most Frequent Character** | [`FindMostFrequent.java`](../../src/main/java/org/example/FindMostFrequent.java) | [Разбор](find-most-frequent-char.md) | `Map`, `LinkedHashMap`, сохранение порядка, $O(N)$ | Easy/Medium |
| **Occupied Seat Rows (Cinema)** | [`OccupiedSeatRows.java`](../../src/main/java/org/example/OccupiedSeatRows.java) | [Разбор](occupied-seat-rows-distance.md) | Жадные алгоритмы, отрезки, граничные условия | Medium |
| **Top 5 Students by Grade** | [`StudentsAndTests.java`](../../src/main/java/org/example/StudentsAndTests.java) | [Разбор](students-top5-average-grade.md) | DTO, Сортировка, `Stream API`, `Comparator` | Easy/Medium |

---

### 💡 Чек-лист прохождения Live Coding раунда
1. **Уточнить условия**: Задать вопросы про граничные случаи (что если строка `null` или пустая? важен ли регистр? как обрабатывать дубликаты?).
2. **Озвучить подход вслух**: Прежде чем кодить, проговорить интервьюеру: *"Я предлагаю использовать `LinkedHashMap`, чтобы за один проход посчитать частоты, а затем найти максимум, сохранив порядок первого появления. Сложность будет $O(N)$"*.
3. **Написать решение**: Писать понятный код, избегать лишних вложенных циклов $O(N^2)$.
4. **Проверить руками**: Прогнать тест-кейсы (пустой ввод, 1 элемент, одинаковая частота).
