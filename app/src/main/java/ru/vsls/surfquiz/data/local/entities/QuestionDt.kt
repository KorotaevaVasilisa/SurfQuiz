package ru.vsls.surfquiz.data.local.entities

import kotlinx.serialization.Serializable

@Serializable
data class QuestionDt(
    val difficulty: String,
    val category: String,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
)