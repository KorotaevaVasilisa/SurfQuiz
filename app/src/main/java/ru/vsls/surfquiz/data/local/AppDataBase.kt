package ru.vsls.surfquiz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.vsls.surfquiz.data.local.dao.QuizDao
import ru.vsls.surfquiz.data.local.entities.ResultQuizDt
import ru.vsls.surfquiz.data.local.entities.UserQuizAnswer

@Database(
    entities = [ResultQuizDt::class,UserQuizAnswer::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getQuizzesDao(): QuizDao
}