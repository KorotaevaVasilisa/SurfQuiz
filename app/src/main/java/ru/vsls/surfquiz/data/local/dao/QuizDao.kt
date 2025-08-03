package ru.vsls.surfquiz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.vsls.surfquiz.data.local.entities.QuizDt

@Dao
interface QuizDao {
    @Insert
    suspend fun insertResult(quiz: QuizDt)

    @Query("SELECT * FROM quizzes_table ORDER BY dateTime DESC")
    suspend fun getAllResults(): List<QuizDt>

    @Query("DELETE FROM quizzes_table WHERE id = :id")
    suspend fun deleteById(id: Int)
}