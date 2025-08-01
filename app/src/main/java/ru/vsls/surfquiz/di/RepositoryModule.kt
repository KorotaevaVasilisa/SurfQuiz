package ru.vsls.surfquiz.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vsls.surfquiz.domain.repository.QuizRepository
import ru.vsls.surfquiz.data.repository.QuizRepositoryImpl
import ru.vsls.surfquiz.domain.usecase.GetQuizzesUseCase
import ru.vsls.surfquiz.domain.usecase.GetQuizzesUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindQuizRepository(
        impl: QuizRepositoryImpl,
    ): QuizRepository

    @Binds
    @Singleton
    abstract fun bindGetQuizzesUseCase(useCaseImpl: GetQuizzesUseCaseImpl): GetQuizzesUseCase
}
