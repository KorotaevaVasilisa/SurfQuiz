package ru.vsls.surfquiz.data.local

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import ru.vsls.surfquiz.data.local.entities.QuestionDt

class Converters {
    // Для List<String>
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString("|")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return if (value.isEmpty()) emptyList() else value.split("|")
    }

    // Для List<QuestionDt>
    @TypeConverter
    fun fromQuestionList(questions: List<QuestionDt>): String {
        return Json.encodeToString(questions)
    }

    @TypeConverter
    fun toQuestionList(value: String): List<QuestionDt> {
        return try {
            Json.decodeFromString(value)
        } catch (e: Exception) {
            emptyList()
        }
    }
}