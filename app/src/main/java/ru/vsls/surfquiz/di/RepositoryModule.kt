package ru.vsls.surfquiz.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vsls.surfquiz.domain.repository.QuizRepository
import ru.vsls.surfquiz.data.repository.QuizRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindQuizRepository(
        impl: QuizRepositoryImpl,
    ): QuizRepository
}
