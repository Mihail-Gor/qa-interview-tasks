---
id: frontend-css-vs-xpath-selectors
title: "CSS-селекторы vs XPath, работа с Shadow DOM и iframe"
category: frontend-automation
difficulty: medium
frequency: high
confidence: 5
last_reviewed: 2026-09-06
tags:
  - web-basics
  - selectors
  - css
  - xpath
  - shadow-dom
  - iframe
---

# ❓ CSS vs XPath, Shadow DOM и iframe

### ⚡ Краткий ответ (TL;DR для повторения за 30–60 сек)
- **CSS-селекторы**: быстрее, лаконичнее, нативно поддерживаются браузером (`document.querySelector`). Не умеют искать по тексту и подниматься вверх по дереву (нет родительских осей, хотя в современных CSS появился псевдокласс `:has()`).
- **XPath**: мощнее, умеет перемещаться во всех направлениях по осям (`parent::`, `ancestor::`, `following-sibling::`), искать по тексту (`text()`, `contains()`).
- **iframe**: изолированный HTML-документ внутри страницы. Перед поиском элементов внутри iframe драйвер **обязан переключиться**: `driver.switchTo().frame(frameElement)`.
- **Shadow DOM**: инкапсулированное дерево DOM. Стандартный `findElement(By.xpath(...))` **не видит** элементы внутри открытого Shadow Root. Нужно получать `shadowRoot` через JS или использовать `getShadowRoot()` / `shadowRoot` в Selenium 4 / Selenide (`$(locator).shadow$()`).

---

### 📖 Развернутый ответ

#### 1. Таблица соответствия CSS и XPath
| Действие | CSS Selector | XPath |
| :--- | :--- | :--- |
| **Поиск по ID** | `#submit-btn` | `//*[@id='submit-btn']` |
| **Поиск по классу** | `.btn.btn-primary` | `//*[contains(@class,'btn') and contains(@class,'btn-primary')]` |
| **По атрибуту** | `input[data-qa='user-email']` | `//input[@data-qa='user-email']` |
| **Подстрока атрибута (начинается с)** | `a[href^='https']` | `//a[starts-with(@href,'https')]` |
| **Подстрока атрибута (содержит)** | `div[class*='active']` | `//div[contains(@class,'active')]` |
| **По тексту** | ❌ (Не поддерживается стандартным CSS) | `//button[text()='Войти']` или `//button[contains(text(),'Войти')]` |
| **Подъем к родителю** | `div:has(> span)` (CSS Level 4) | `//span/parent::div` или `//span/..` |

#### 2. Работа с Shadow DOM в Selenium 4
```java
WebElement shadowHost = driver.findElement(By.cssSelector("custom-component"));
SearchContext shadowRoot = shadowHost.getShadowRoot();
WebElement shadowButton = shadowRoot.findElement(By.cssSelector("button.action"));
shadowButton.click();
```

---

### 🔥 Каверзные вопросы на собеседовании

1. *Почему абсолютный XPath (`/html/body/div[2]/div[1]/...`) — это жесткий антипаттерн?*
   - **Ответ**: Любое минимальное изменение разметки фронтенд-разработчиком ломает локатор. Всегда следует строить короткие относительные селекторы от уникальных стабильных атрибутов (`data-testid`, `id`, `aria-label`).

2. *Как переключиться обратно на основную страницу после работы с `iframe`?*
   - **Ответ**: `driver.switchTo().defaultContent()`. Если были вложенные фреймы и нужно выйти на 1 уровень вверх: `driver.switchTo().parentFrame()`.
