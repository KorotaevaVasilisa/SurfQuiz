package ru.vsls.surfquiz.presentation.screens.history

import ru.vsls.surfquiz.domain.model.QuizHistoryEntry

data class HistoryUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val history: List<QuizHistoryEntry> = emptyList(),
)