package ru.vsls.surfquiz.presentation.screens.history

import ru.vsls.surfquiz.domain.model.QuizHistoryEntry
import ru.vsls.surfquiz.presentation.screens.history.QuizHistoryUiModel

data class HistoryUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val history: List<QuizHistoryUiModel> = emptyList(),
    val selectedItemId: Long? = null,
)