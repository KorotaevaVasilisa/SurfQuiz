package ru.vsls.surfquiz.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * DTO вопроса с вариантами ответов
 * @param type Тип вопроса
 * @param difficulty Сложность
 * @param category Категория
 * @param question Текст вопроса
 * @param correctAnswer Правильный ответ
 * @param incorrectAnswers Список неправильных вариантов
 */
@Serializable
data class QuestionDto(
    val type: String,
    val difficulty: String,
    val category: String,
    val question: String,
    @SerialName("correct_answer") val correctAnswer: String,
    @SerialName("incorrect_answers") val incorrectAnswers: List<String>,
)
