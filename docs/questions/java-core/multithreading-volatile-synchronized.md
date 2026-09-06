---
id: java-multithreading-volatile-synchronized
title: "Ключевые слова volatile, synchronized и Java Memory Model (JMM)"
category: java-core
difficulty: medium
frequency: high
confidence: 5
last_reviewed: 2026-09-06
tags:
  - multithreading
  - volatile
  - synchronized
  - jmm
  - happens-before
---

# ❓ volatile vs synchronized и Java Memory Model

### ⚡ Краткий ответ (TL;DR для повторения за 30–60 сек)
- `volatile` гарантирует **видимость (visibility)** изменений переменной между потоками (чтение/запись напрямую из оперативной памяти, минуя L1/L2 кэши процессора) и **запрещает переупорядочивание инструкций (instruction reordering)** через Memory Barriers. **НЕ гарантирует атомарность** (например, `count++` не потокобезопасен!).
- `synchronized` гарантирует **видимость + взаимное исключение (mutual exclusion / атомарность)**. Только один поток может захватить монитор объекта.
- Отношение **Happens-Before** гарантирует, что запись в `volatile` или освобождение монитора `synchronized` видны последующему чтению другим потоком.

---

### 📖 Развернутый ответ

#### 1. Сравнение volatile и synchronized
| Свойство | `volatile` | `synchronized` |
| :--- | :--- | :--- |
| **Что гарантирует?** | Видимость + Порядок операций | Видимость + Атомарность + Взаимное исключение |
| **Блокирует ли потоки?** | Нет (Non-blocking) | Да (потоки переходят в состояние `BLOCKED`) |
| **Где применяется?** | Только к полям класса | К методам или блокам кода |
| **Атомарность `i++`?** | ❌ Нет (`i++` состоит из 3 инструкций: read, modify, write) | ✅ Да (при оборачивании в синхронизированный блок) |

#### 2. Правила Happens-Before (JMM)
- **Program Order**: Каждое действие в потоке происходит до любого действия, идущего позже по коду.
- **Monitor Lock Rule**: Освобождение блокировки (`unlock`) happens-before захвату этой же блокировки (`lock`).
- **Volatile Variable Rule**: Запись в `volatile` переменную happens-before чтению из этой же переменной.
- **Thread Start / Join**: Вызов `thread.start()` happens-before первому действию в потоке; завершение потока happens-before возврату из `thread.join()`.
- **Transitivity (Транзитивность)**: Если A happens-before B, и B happens-before C, то A happens-before C.

---

### 🔥 Каверзные вопросы на собеседовании

1. *Как реализовать потокобезопасный счетчик без `synchronized`?*
   - **Ответ**: Использовать `AtomicInteger` (внутри использует `CAS`) или `LongAdder` (для высокой конкуренции).

2. *Почему в паттерне Double-Checked Locking для Singleton поле обязательно должно быть `volatile`?*
   ```java
   private static volatile Singleton instance;
   ```
   - **Ответ**: Создание объекта `new Singleton()` состоит из 3 шагов: 1) выделение памяти, 2) вызов конструктора, 3) присвоение ссылки. Без `volatile` JVM может переупорядочить шаги 2 и 3, и другой поток получит не полностью инициализированный объект.
