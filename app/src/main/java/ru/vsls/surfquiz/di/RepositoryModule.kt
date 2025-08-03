package ru.vsls.surfquiz.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vsls.surfquiz.data.local.QuizLocalRepositoryImpl
import ru.vsls.surfquiz.domain.repository.QuizRepository
import ru.vsls.surfquiz.data.repository.QuizRepositoryImpl
import ru.vsls.surfquiz.domain.repository.QuizLocalRepository
import ru.vsls.surfquiz.domain.usecase.GetQuizzesUseCase
import ru.vsls.surfquiz.domain.usecase.GetQuizzesUseCaseImpl
import ru.vsls.surfquiz.domain.usecase.GetQuizHistoryUseCase
import ru.vsls.surfquiz.domain.usecase.GetQuizHistoryUseCaseImpl
import ru.vsls.surfquiz.domain.usecase.SaveQuizHistoryUseCase
import ru.vsls.surfquiz.domain.usecase.SaveQuizHistoryUseCaseImpl
import ru.vsls.surfquiz.domain.usecase.DeleteQuizHistoryEntryUseCase
import ru.vsls.surfquiz.domain.usecase.DeleteQuizHistoryEntryUseCaseImpl
import ru.vsls.surfquiz.domain.usecase.GetQuizDetailsUseCase
import ru.vsls.surfquiz.domain.usecase.GetQuizDetailsUseCaseImpl
import ru.vsls.surfquiz.domain.usecase.SaveQuizDetailsUseCase
import ru.vsls.surfquiz.domain.usecase.SaveQuizDetailsUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindQuizRepository(impl: QuizRepositoryImpl): QuizRepository

    @Binds
    @Singleton
    abstract fun bindGetQuizzesUseCase(useCaseImpl: GetQuizzesUseCaseImpl): GetQuizzesUseCase

    @Binds
    @Singleton
    abstract fun bindQuizLocalRepository(impl: QuizLocalRepositoryImpl): QuizLocalRepository

    @Binds
    @Singleton
    abstract fun bindGetQuizHistoryUseCase(impl: GetQuizHistoryUseCaseImpl): GetQuizHistoryUseCase

    @Binds
    @Singleton
    abstract fun bindSaveQuizHistoryUseCase(impl: SaveQuizHistoryUseCaseImpl): SaveQuizHistoryUseCase

    @Binds
    @Singleton
    abstract fun bindDeleteQuizHistoryEntryUseCase(impl: DeleteQuizHistoryEntryUseCaseImpl): DeleteQuizHistoryEntryUseCase

    @Binds
    @Singleton
    abstract fun bindSaveQuizDetailsUseCase(impl: SaveQuizDetailsUseCaseImpl): SaveQuizDetailsUseCase

    @Binds
    @Singleton
    abstract fun bindGetQuizDetailsUseCase(impl: GetQuizDetailsUseCaseImpl): GetQuizDetailsUseCase
}
