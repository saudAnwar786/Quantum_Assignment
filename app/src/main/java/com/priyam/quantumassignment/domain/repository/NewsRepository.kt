package com.priyam.quantumassignment.domain.repository

import com.priyam.quantumassignment.data.model.APIResponse
import com.priyam.quantumassignment.data.util.Resource

interface NewsRepository {
    suspend fun getNewsHeadlines() : Resource<APIResponse>
    suspend fun getSearchedNews(searchQuery:String) : Resource<APIResponse>

}