package ru.vsls.surfquiz.data.local.mappers

import ru.vsls.surfquiz.data.local.entities.ResultQuizDt
import ru.vsls.surfquiz.domain.model.QuizHistoryEntry

fun ResultQuizDt.toDomain(): QuizHistoryEntry = QuizHistoryEntry(
    id = id,
    dateTime = dateTime,
    correctAnswers = correctAnswers,
    totalQuestions = totalQuestions,
    difficulty = difficulty
)

fun QuizHistoryEntry.toEntity(): ResultQuizDt = ResultQuizDt(
    id = id,
    dateTime = dateTime,
    correctAnswers = correctAnswers,
    totalQuestions = totalQuestions,
    difficulty = difficulty
)
