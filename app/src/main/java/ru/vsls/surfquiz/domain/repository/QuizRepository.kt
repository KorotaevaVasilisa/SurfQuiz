package ru.vsls.surfquiz.domain.repository

import ru.vsls.surfquiz.domain.model.Question

/**
 * Репозиторий для получения данных о квизах
 */
interface QuizRepository {
    suspend fun getQuizzes(
        amount: Int = 5,
        difficulty: String = "medium",
        type: String = "multiple",
    ): List<Question>
}
