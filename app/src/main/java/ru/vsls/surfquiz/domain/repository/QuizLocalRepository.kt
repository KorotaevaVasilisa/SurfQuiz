package ru.vsls.surfquiz.domain.repository

import ru.vsls.surfquiz.data.local.entities.QuizDt

interface QuizLocalRepository {
        suspend fun saveResult(result: QuizDt)
        suspend fun getHistory(): List<QuizDt>
        suspend fun deleteById(id: Int)
}