package ru.vsls.surfquiz.domain.usecase

import ru.vsls.surfquiz.domain.repository.QuizLocalRepository
import javax.inject.Inject

interface DeleteQuizHistoryEntryUseCase {
    suspend operator fun invoke(id: Int)
}

class DeleteQuizHistoryEntryUseCaseImpl @Inject constructor(
    private val repository: QuizLocalRepository,
) : DeleteQuizHistoryEntryUseCase {
    override suspend fun invoke(id: Int) = repository.deleteById(id)
}
