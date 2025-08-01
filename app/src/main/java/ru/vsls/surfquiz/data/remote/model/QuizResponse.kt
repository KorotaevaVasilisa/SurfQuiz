package ru.vsls.surfquiz.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
/**
 * Корневой ответ API квизов
 * @param responseCode Код ответа
 * @param results Список вопросов
 */
@Serializable
data class QuizResponse(
    @SerialName("response_code") val responseCode: Int,
    val results: List<QuestionDto>,
)
