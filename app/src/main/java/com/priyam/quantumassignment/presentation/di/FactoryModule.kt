package com.priyam.quantumassignment.presentation.di

import android.app.Application
import com.priyam.quantumassignment.domain.usecase.GetNewsHeadlinesUseCase
import com.priyam.quantumassignment.domain.usecase.GetSearchedNewsUseCase
import com.priyam.quantumassignment.presentation.viewModel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(
            application,
            getNewsHeadlinesUseCase,
            getSearchedNewsUseCase
        )
    }

}
