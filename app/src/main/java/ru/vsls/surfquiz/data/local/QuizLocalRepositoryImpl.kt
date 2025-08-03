package ru.vsls.surfquiz.data.local

import ru.vsls.surfquiz.data.local.dao.QuizDao
import ru.vsls.surfquiz.data.local.entities.QuizDt
import ru.vsls.surfquiz.domain.repository.QuizLocalRepository
import javax.inject.Inject

class QuizLocalRepositoryImpl @Inject constructor(private val api: QuizDao): QuizLocalRepository
{
    override suspend fun saveResult(result: QuizDt) {
        api.insertResult(result)
    }

    override suspend fun getHistory(): List<QuizDt> {
        return api.getAllResults()
    }

    override suspend fun deleteById(id: Int) {
        api.deleteById(id)
    }
}