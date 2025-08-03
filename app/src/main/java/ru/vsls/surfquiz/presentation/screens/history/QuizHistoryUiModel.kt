package ru.vsls.surfquiz.presentation.screens.history

import ru.vsls.surfquiz.domain.model.QuizHistoryEntry
import java.text.SimpleDateFormat
import java.util.*

data class QuizHistoryUiModel(
    val id: Long,
    val title: String,
    val formattedDate: String,
    val correctAnswers: Int,
    val totalQuestions: Int,
    val difficulty: String,
)

fun List<QuizHistoryEntry>.toUiModels(): List<QuizHistoryUiModel> =
    mapIndexed { index, entry ->
        QuizHistoryUiModel(
            id = entry.id,
            title = "Quiz ${index + 1}",
            formattedDate = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(
                Date(
                    entry.dateTime
                )
            ),
            correctAnswers = entry.correctAnswers,
            totalQuestions = entry.totalQuestions,
            difficulty = entry.difficulty
        )
    }
