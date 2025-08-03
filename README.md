# SurfQuiz

**SurfQuiz** — мобильное приложение-викторина на Android с архитектурой Clean Architecture + MVVM +
Jetpack Compose + Room, DI через Hilt и поддержкой сохранения и просмотра истории квизов.


## 🚀 Функционал

- Прохождение викторины с множеством вопросов
- Мгновенная проверка ответа с подсветкой результата
- Поддержка разных уровней сложности и категорий вопросов
- Подсчет и отображение результата по завершении квиза
- Сохранение истории квизов и ответов пользователя в локальную БД
- Просмотр полной истории прохождения (дата, счет, сложность, список вопросов и ответов)
- Просмотр подробных детальных ответов по каждому завершённому квизу
- Современный интерфейс на Jetpack Compose с Material 3
- Устойчивость к поворотам экрана и перезапускам

---

## 🏗️ Архитектура проекта

- **Clean Architecture** (разделение на domain, data, presentation)
- **MVVM** (Model-View-ViewModel через StateFlow)
- **Jetpack Compose** (UI)
- **Room** (база данных — история квизов и ответы)
- **Hilt** (Dependency Injection)
- **Kotlin Coroutines/Flow** (асинхронность и реактивность)

### Основные слои:

- `data` — Room entities, DAO, DataSource, TypeConverters, репозитории
- `domain` — бизнес-логика, Entity, UseCase
- `presentation` — ViewModel, UiState, Jetpack Compose-экраны и элементы
- `di` — настройки Hilt для зависимостей


## ⚙️ Как запустить проект

1. **Клонируй репозиторий:**
    ```bash
    git clone https://github.com/your-username/surfquiz.git
    ```
2. **Открой в Android Studio**
3. **Сборка и запуск:**
    - Убедись, что выбран модуль `app`
    - Запусти на эмуляторе или реальном устройстве 

---

## 🏛️ Структура проекта

```
app/src/main/java/ru/vsls/surfquiz/
│
├─ data/               // Data layer (Room, DAO, репозитории, мапперы)
│    ├─ local/
│    │    ├─ entities/ // Room entities (ResultQuizDt, UserQuizAnswer, QuestionDt)
│    │    ├─ dao/      // DAO интерфейсы
│    │    └─ AppDataBase.kt
│    ├─ remote/
│    │    ├─ model/
│    │    └─ api/      // DAO интерфейсы
│
├─ domain/             // Domain layer (use case, модели, interface репозиториев)
│    ├─ model/
│    ├─ usecase/
│    └─ repository/
│
├─ presentation/       // Presentation layer (ViewModel, UI, state)
│    ├─ items/         // Компоненты Compose (QuizQuestionBlock, QuizResultBlock и др.)
│    ├─ screens/
│    │    ├─ start/
│    │    ├─ history/
│    │    └─ detailed/
│    └─ navigation/    // Навигация между экранами
│
├─ ui/theme/           // Ваш дизайн и цвета
│
├─ di/                 // DI-модули для Hilt
```
