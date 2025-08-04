package ru.vsls.surfquiz.data.remote.mapper

import ru.vsls.surfquiz.data.remote.model.QuestionDto
import ru.vsls.surfquiz.domain.model.Question

fun QuestionDto.toDomain(): Question = Question(
    category = category,
    difficulty = difficulty,
    question = htmlDecode(question),
    correctAnswer = htmlDecode(correctAnswer),
    incorrectAnswers = incorrectAnswers.map { htmlDecode(it) }
)

fun List<QuestionDto>.toDomain(): List<Question> = map { it.toDomain() }

fun htmlDecode(source: String): String {
    return source
        .replace("&quot;", "\"")
        .replace("&amp;", "&")
        .replace("&#039;", "'")
        .replace("&lt;", "<")
        .replace("&gt;", ">")
        .replace("&eacute;", "é")
        .replace("&auml;", "ä")
        .replace("&ouml;", "ö")
        .replace("&aacute;", "á")
        .replace("&oacute;", "ó")
        .replace("&ntilde;", "ñ")
}
