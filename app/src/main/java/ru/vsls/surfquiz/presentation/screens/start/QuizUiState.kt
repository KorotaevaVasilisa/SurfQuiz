package ru.vsls.surfquiz.presentation.screens.start

import ru.vsls.surfquiz.domain.model.Question

data class QuizUiState(
    val isLoading: Boolean = false,
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedAnswer: String? = null,
    val userAnswers: List<String> = emptyList(),
    val error: String? = null,
    val quizFinished: Boolean = false,
    val correctCount: Int = 0,
    val isAnswerCorrect: Boolean? = null,
    val isInteractionBlocked: Boolean = false,
)