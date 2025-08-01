package ru.vsls.surfquiz.domain.usecase

import ru.vsls.surfquiz.domain.model.Question
import ru.vsls.surfquiz.domain.repository.QuizRepository
import javax.inject.Inject

/**
 * UseCase для получения квизовых вопросов
 */
class GetQuizzesUseCase @Inject constructor(
    private val repository: QuizRepository,
) {
    suspend operator fun invoke(
        amount: Int = 5,
        difficulty: String = "medium",
        type: String = "multiple",
    ): List<Question> = repository.getQuizzes(amount, difficulty, type)
}
