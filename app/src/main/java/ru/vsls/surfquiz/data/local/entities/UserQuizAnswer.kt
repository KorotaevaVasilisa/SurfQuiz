package ru.vsls.surfquiz.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ResultQuizDt::class,
            parentColumns = ["id"],
            childColumns = ["resultId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    tableName = UserQuizAnswer.DETAILS_TABLE_NAME
)
data class UserQuizAnswer(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val resultId: Long,
    val correctCount: Int,
    val usersAnswers:List<String>,
    val questions: List<QuestionDt>
){
    companion object {
        const val DETAILS_TABLE_NAME = "answers_table"
    }
}
