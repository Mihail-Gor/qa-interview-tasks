---
id: frontend-selenium-waits-stale-element
title: "Selenium vs Selenide: Неявные vs Явные ожидания, борьба со StaleElementReferenceException"
category: frontend-automation
difficulty: medium
frequency: high
confidence: 5
last_reviewed: 2026-09-06
tags:
  - ui-automation
  - selenium
  - selenide
  - waits
  - stale-element
  - exceptions
---

# ❓ Ожидания в Selenium/Selenide и StaleElementReferenceException

### ⚡ Краткий ответ (TL;DR для повторения за 30–60 сек)
- **Implicit Wait (Неявное ожидание)**: глобальная настройка на уровне WebDriver для поиска элементов (`findElement`). Ждет появления элемента в DOM. **Смешивать с Explicit Wait запрещено** (непредсказуемые таймауты).
- **Explicit Wait (Явное ожидание)**: `WebDriverWait` + `ExpectedConditions`. Ждет конкретного состояния (видимость `visibilityOf`, кликабельность `elementToBeClickable`).
- **Fluent Wait**: расширенное явное ожидание с настройкой частоты опроса (`pollingEvery`) и игнорированием исключений (`ignoring(NoSuchElementException.class)`).
- **StaleElementReferenceException**: возникает, когда элемент был найден в DOM, но затем DOM обновился (AJAX, SPA, перерисовка React/Vue) и старая ссылка на элемент в памяти драйвера протухла.
- **Selenide**: решает эту проблему из коробки с помощью **Lazy Loading (ленивых элементов)** и автоматических повторных попыток (Smart Retry).

---

### 📖 Развернутый ответ

#### 1. Как победить StaleElementReferenceException
1. **В чистом Selenium WebDriver**:
   - Переинициализировать элемент заново перед действием (`driver.findElement(locator).click()`).
   - Использовать `WebDriverWait` с повторным поиском:
     ```java
     new WebDriverWait(driver, Duration.ofSeconds(10))
         .ignoring(StaleElementReferenceException.class)
         .until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)))
         .click();
     ```
2. **В Selenide**:
   - Использовать `$(locator).shouldBe(visible).click();` — Selenide не кэширует WebElement, а заново ищет его в DOM в момент каждого действия, повторяя попытки в течение 4 секунд.

#### 2. Почему нельзя смешивать Implicit и Explicit Waits в Selenium
Официальная документация Selenium предупреждает: смешивание приводит к непредсказуемому времени ожидания. Драйвер может ожидать `max(implicit, explicit)` или даже их сумму `implicit + explicit`, что фатально замедляет прогон тестов при падениях. Лучшая практика: **implicit wait = 0**, использовать только явные ожидания.

---

### 🔥 Каверзные вопросы на собеседовании

1. *В чем разница между `NoSuchElementException` и `StaleElementReferenceException`?*
   - `NoSuchElementException`: элемент вообще ни разу не был найден в DOM по указанному локатору в течение времени ожидания.
   - `StaleElementReferenceException`: элемент **был успешно найден**, но к моменту клика/ввода страница перерисовалась, и элемент был удален или пересоздан в DOM дереве.

2. *В чем разница между `driver.close()` и `driver.quit()`?*
   - `driver.close()`: закрывает только текущую активную вкладку/окно браузера.
   - `driver.quit()`: закрывает все окна браузера, корректно убивает процесс ChromeDriver/GeckoDriver и освобождает порты.
