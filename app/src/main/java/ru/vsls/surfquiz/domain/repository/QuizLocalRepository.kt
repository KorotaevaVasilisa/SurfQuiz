package ru.vsls.surfquiz.domain.repository

import ru.vsls.surfquiz.data.local.entities.ResultQuizDt

interface QuizLocalRepository {
        suspend fun saveResult(result: ResultQuizDt)
        suspend fun getHistory(): List<ResultQuizDt>
        suspend fun deleteById(id: Long)
}