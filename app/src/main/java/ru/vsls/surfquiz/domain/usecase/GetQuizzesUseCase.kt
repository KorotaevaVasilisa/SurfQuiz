package ru.vsls.surfquiz.domain.usecase

import ru.vsls.surfquiz.domain.model.Question
import ru.vsls.surfquiz.domain.repository.QuizRepository
import javax.inject.Inject

/**
 * UseCase для получения квизовых вопросов
 */

interface GetQuizzesUseCase {
    suspend operator fun invoke(
        amount: Int = 5,
        difficulty: String = "medium",
        type: String = "multiple",
    ): List<Question>
}

class GetQuizzesUseCaseImpl @Inject constructor(
    private val repository: QuizRepository,
): GetQuizzesUseCase {
    override suspend fun invoke(
        amount: Int,
        difficulty: String,
        type: String,
    ): List<Question> {
        return repository.getQuizzes(amount, difficulty, type)
    }
}
