package ru.vsls.surfquiz.data.local.entities

data class QuestionDt(
    val type: String,
    val difficulty: String,
    val category: String,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
)