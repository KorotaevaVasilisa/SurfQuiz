# Android Compose Clean Code Guidelines

## 📚 Архитектура

### Слои приложения (Clean Architecture)

```shell
app/
├── data/        # Реализация источников данных (Retrofit, Room)
├── domain/      # UseCases, Entities, Repository interfaces
└── presenter/   # ViewModels, Composable-экран
```

### Обязательные технологии

1. **DI**: Hilt
2. **Local DB**: Room
3. **Networking**: Retrofit + Kotlin Serialization
4. **State Management**: Compose + ViewModel

### Примерная структура слоев и моделей

#### data/

```kotlin
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()
}
```

#### domain/

```kotlin
class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): List<User> = repository.getUsers()
}
```

#### presenter/

```kotlin
@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    private val _state = mutableStateOf(UserListState())
    val state: State<UserListState> = _state

    fun loadData() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            _state.value = try {
                val users = getUsersUseCase()
                _state.value.copy(users = users, isLoading = false)
            } catch (e: Exception) {
                _state.value.copy(error = e.message, isLoading = false)
            }
        }
    }
}
```

---

## 📝 Документирование

### Обязательные комментарии:

- Только перед классами и методами
- Формат — KDoc

#### Пример:

```kotlin
/**
 * Репозиторий для работы с данными пользователя
 * @param apiSource Источник API данных
 * @param localSource Локальная БД (Room)
 */
class UserRepositoryImpl @Inject constructor(
    private val apiSource: UserApi,
    private val localSource: UserDao
) : UserRepository
```

## 🗄 Для Room + Compose (пример Dao)

```kotlin
@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun observeUsers(): List<User>
}
```