package ru.vsls.surfquiz.data.local.mappers

import ru.vsls.surfquiz.data.local.entities.UserQuizAnswer
import ru.vsls.surfquiz.data.local.entities.QuestionDt
import ru.vsls.surfquiz.domain.model.QuizDetailsEntry
import ru.vsls.surfquiz.domain.model.Question


fun QuizDetailsEntry.toUserQuizAnswerEntity(): UserQuizAnswer =
    UserQuizAnswer(
        id = id, // если генерируется Room, можно 0L или не передавать вовсе
        resultId = resultId,
        correctCount = correctCount,
        usersAnswers = usersAnswers,
        questions = questions.map { it.toQuestionDt() }
    )

fun Question.toQuestionDt(): QuestionDt =
    QuestionDt(
        difficulty = difficulty,
        category = category,
        question = question,
        correctAnswer = correctAnswer,
        incorrectAnswers = incorrectAnswers
    )

fun UserQuizAnswer.toDomain(questions: List<Question> = emptyList()): QuizDetailsEntry =
    QuizDetailsEntry(
        id = id,
        resultId = resultId,
        correctCount = correctCount,
        usersAnswers = usersAnswers,
        questions = if (questions.isNotEmpty()) questions else this.questions.map { it.toDomain() }
    )

fun QuestionDt.toDomain(): Question =
    Question(
        category = category,
        difficulty = difficulty,
        question = question,
        correctAnswer = correctAnswer,
        incorrectAnswers = incorrectAnswers
    )
