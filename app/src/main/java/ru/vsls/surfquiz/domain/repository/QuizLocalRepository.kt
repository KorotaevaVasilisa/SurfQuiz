package ru.vsls.surfquiz.domain.repository

import ru.vsls.surfquiz.data.local.entities.ResultQuizDt
import ru.vsls.surfquiz.data.local.entities.UserQuizAnswer

interface QuizLocalRepository {
    suspend fun saveResult(result: ResultQuizDt):Long
    suspend fun getHistory(): List<ResultQuizDt>
    suspend fun deleteById(id: Long)
    suspend fun getDetails(id: Long): UserQuizAnswer
    suspend fun saveDetails(details: UserQuizAnswer)
}