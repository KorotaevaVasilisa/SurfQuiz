package ru.vsls.surfquiz.domain.model

data class QuizHistoryEntry(
    val id: Long = 0,
    val dateTime: Long,
    val correctAnswers: Int,
    val totalQuestions: Int,
    val difficulty: String,
)
