package ru.vsls.surfquiz.data.repository

import ru.vsls.surfquiz.domain.model.Question
import ru.vsls.surfquiz.domain.repository.QuizRepository
import ru.vsls.surfquiz.data.remote.QuizApiService
import ru.vsls.surfquiz.data.mapper.toDomain
import javax.inject.Inject

/**
 * Реализация QuizRepository
 */
class QuizRepositoryImpl @Inject constructor(
    private val api: QuizApiService,
) : QuizRepository {
    override suspend fun getQuizzes(
        amount: Int,
        difficulty: String,
        type: String,
    ): List<Question> {
        val response = api.getQuiz(amount, difficulty, type)
        return response.results.toDomain()
    }
}
