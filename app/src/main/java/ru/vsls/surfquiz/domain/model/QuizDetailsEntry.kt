package ru.vsls.surfquiz.domain.model


data class QuizDetailsEntry(
    val id: Long = 0,
    val resultId: Long,
    val correctCount: Int,
    val usersAnswers:List<String>,
    val questions: List<Question>
)
