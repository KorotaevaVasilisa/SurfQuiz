package ru.vsls.surfquiz.data.mapper

import ru.vsls.surfquiz.data.remote.model.QuestionDto
import ru.vsls.surfquiz.domain.model.Question

fun QuestionDto.toDomain(): Question = Question(
    category = category,
    difficulty = difficulty,
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswers = incorrectAnswers
)

fun List<QuestionDto>.toDomain(): List<Question> = map { it.toDomain() }
