package com.priyam.quantumassignment.domain.usecase

import com.priyam.quantumassignment.data.model.APIResponse
import com.priyam.quantumassignment.data.util.Resource
import com.priyam.quantumassignment.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(searchQuery: String, searchQuery1: String, page: Int): Resource<APIResponse> {
        return newsRepository.getSearchedNews(searchQuery)
    }

}