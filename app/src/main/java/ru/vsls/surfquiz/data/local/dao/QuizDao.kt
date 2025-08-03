package ru.vsls.surfquiz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.vsls.surfquiz.data.local.entities.ResultQuizDt
import ru.vsls.surfquiz.data.local.entities.UserQuizAnswer

@Dao
interface QuizDao {
    @Insert
    suspend fun insertResult(quiz: ResultQuizDt):Long

    @Query("SELECT * FROM quizzes_table ORDER BY dateTime DESC")
    suspend fun getAllResults(): List<ResultQuizDt>

    @Query("DELETE FROM quizzes_table WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("SELECT * FROM answers_table WHERE id = :id")
    suspend fun getDetailsById(id: Long): UserQuizAnswer

    @Insert
    suspend fun insertDetails(answer: UserQuizAnswer)
}