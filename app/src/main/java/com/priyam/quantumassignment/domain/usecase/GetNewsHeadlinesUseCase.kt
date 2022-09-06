package com.priyam.quantumassignment.domain.usecase

import com.priyam.quantumassignment.data.model.APIResponse
import com.priyam.quantumassignment.data.util.Resource
import com.priyam.quantumassignment.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository){
    suspend fun execute(country:String , page:Int): Resource<APIResponse> {
        return newsRepository.getNewsHeadlines(country,page)
    }
}