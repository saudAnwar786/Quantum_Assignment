package com.priyam.quantumassignment.presentation.di

import com.priyam.quantumassignment.data.api.NewsAPIService
import com.priyam.quantumassignment.data.repository.dataSource.NewsRemoteDataSource
import com.priyam.quantumassignment.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsAPIService: NewsAPIService
    ): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsAPIService)
    }

}
