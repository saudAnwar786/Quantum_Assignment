package com.priyam.quantumassignment.domain.usecase

import com.priyam.quantumassignment.data.model.APIResponse
import com.priyam.quantumassignment.data.util.Resource
import com.priyam.quantumassignment.domain.repository.NewsRepository

class GetNewHeadlinesUseCase(private val newsRepository: NewsRepository){
    suspend fun execute(): Resource<APIResponse> {
        return newsRepository.getNewsHeadlines()
    }
}