package ru.vsls.surfquiz.presentation

import ru.vsls.surfquiz.domain.model.Question

data class QuizUiState(
    val isLoading: Boolean = false,
    val questions: List<Question> = emptyList(),
    val error: String? = null,
)
