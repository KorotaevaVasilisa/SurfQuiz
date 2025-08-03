package ru.vsls.surfquiz.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ResultQuizDt.DETAILS_TABLE_NAME)
data class ResultQuizDt(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dateTime: Long,
    val correctAnswers: Int,
    val totalQuestions: Int,
    val difficulty: String
) {
    companion object {
        const val DETAILS_TABLE_NAME = "quizzes_table"
    }
}
