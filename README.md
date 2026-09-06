# 🎯 Java QA Automation Interview Hub & Knowledge Base

> Полноценный сайт и структурированная база знаний для подготовки к собеседованиям на позицию **Middle / Senior Java QA Automation Engineer (Backend + Frontend)**.

Готовый сайт работает на **Docsify** и опубликован на **GitHub Pages** с поддержкой поиска, темной/светлой темы, копирования сниппетов кода, навигации по категориям и карточек с метаданными.

---

## 🚀 Как устроен сайт на GitHub Pages

Все исходные материалы сайта находятся в директории `docs/`:
- **`docs/index.html`** — конфигурация Docsify, плагины полнотекстового поиска, подсветки синтаксиса Prism (Java, SQL, Bash, YAML), генератор бейджей и переключатель темной темы.
- **`docs/_sidebar.md`** — интерактивное боковое меню со структурой тем.
- **`docs/_navbar.md`** — верхнее меню для быстрого переключения разделов.
- **`docs/_coverpage.md`** — лендинг-обложка с быстрыми ссылками.
- **`docs/assets/custom.css`** — современная кастомная тема, стили для TL;DR выжимок, каверзных вопросов и бейджей сложности.
- **`docs/.nojekyll`** — служебный файл для GitHub Pages.
- **`docs/questions/`** — база вопросов и ответов с YAML-frontmatter.
- **`docs/interviews/`** — ретроспективы и трекер пробелов (`gaps-backlog.md`).

---

## 🌐 Публикация на GitHub Pages

### Вариант 1. Автоматический деплой через GitHub Actions (Рекомендуется)
В проект добавлен workflow `.github/workflows/deploy-pages.yml`.
1. Перейдите в настройки репозитория: **Settings ➔ Pages**.
2. В секции **Build and deployment** ➔ **Source** выберите **GitHub Actions**.
3. При любом коммите в ветку `main` сайт будет автоматически собираться и публиковаться.

### Вариант 2. Деплой из ветки и папки `docs/`
1. Перейдите в **Settings ➔ Pages**.
2. В секции **Source** выберите **Deploy from a branch**.
3. Выберите ветку `main` (или `master`) и папку `/docs`.
4. Нажмите **Save**.

---

## 💻 Локальный просмотр сайта

Вы можете запустить сайт локально любой удобной командой:

### Через `npx docsify-cli` (Node.js):
```bash
npx docsify-cli serve docs
```
Сайт будет доступен по адресу: `http://localhost:3000`

### Через Python:
```bash
# Python 3
python -m http.server 3000 --directory docs
```
Сайт будет доступен по адресу: `http://localhost:3000`

---

## 🧭 Структура базы знаний

```text
├── .github/workflows/
│   └── deploy-pages.yml               # Автодеплой на GitHub Pages
├── docs/                              # Исходники сайта для GitHub Pages
│   ├── index.html                     # Главная страница Docsify
│   ├── _sidebar.md                    # Боковое меню
│   ├── _navbar.md                     # Верхняя панель навигации
│   ├── _coverpage.md                  # Обложка сайта
│   ├── assets/custom.css              # Стили и бейджи
│   ├── questions/                     # База вопросов
│   │   ├── java-core/                 # HashMap, ConcurrentHashMap, Volatile, JMM, JVM Memory, GC
│   │   ├── algorithms-tasks/          # Live Coding разборы (FindMostFrequent, OccupiedSeatRows, Top5Students)
│   │   ├── backend-automation/        # REST API, REST Assured, SQL, ACID, Kafka, Awaitility
│   │   ├── frontend-automation/       # CSS/XPath, Shadow DOM, Selenium/Selenide ожидания
│   │   ├── frameworks-ci-cd/          # JUnit 5 lifecycle, параллелизация
│   │   ├── architecture-design/       # Enterprise архитектура фреймворка
│   │   └── testing-theory/            # Техники тест-дизайна
│   ├── interviews/                    # Ретроспективы и трекер пробелов
│   │   ├── gaps-backlog.md            # Сводный бэклог вопросов
│   │   └── 2026/                      # Архив дебрифингов
│   └── templates/                     # Шаблоны вопросов и интервью
│
├── src/                               # Исходный код Java задач и тестов
    ├── main/java/org/example/         # Реализации алгоритмических задач
    └── test/java/org/example/         # Автотесты (JUnit 5 + AssertJ)
```

---

## 🧪 Запуск автотестов Live Coding задач

В проекте настроены тесты для проверки алгоритмических задач:
```bash
mvn clean test
```
Тесты покрывают позитивные, негативные и граничные сценарии для задач `FindMostFrequent`, `OccupiedSeatRows` и `StudentsAndTests`.
