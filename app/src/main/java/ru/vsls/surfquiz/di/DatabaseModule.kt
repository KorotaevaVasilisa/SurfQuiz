package ru.vsls.surfquiz.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.vsls.surfquiz.data.local.AppDataBase
import ru.vsls.surfquiz.data.local.dao.QuizDao
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "app_database.db"
        )
            .build()
    }

    @Provides
    fun provideTrackDao(database: AppDataBase): QuizDao {
        return database.getQuizzesDao()
    }
}