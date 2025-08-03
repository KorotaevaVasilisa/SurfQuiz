package ru.vsls.surfquiz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.vsls.surfquiz.data.local.dao.QuizDao
import ru.vsls.surfquiz.data.local.entities.QuizDt

@Database(
    entities = [QuizDt::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getQuizzesDao(): QuizDao
}