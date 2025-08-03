package ru.vsls.surfquiz.presentation.screens.detailed

import ru.vsls.surfquiz.domain.model.QuizDetailsEntry

data class DetailsUiState(
    val isLoading: Boolean = false,
    val details: QuizDetailsEntry? = null,
    val error: String? = null
)
