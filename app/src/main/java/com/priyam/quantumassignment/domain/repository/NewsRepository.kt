package com.priyam.quantumassignment.domain.repository

import com.priyam.quantumassignment.data.model.APIResponse
import com.priyam.quantumassignment.data.util.Resource

interface NewsRepository {
    suspend fun getNewsHeadlines(country:String , page:Int) : Resource<APIResponse>
    suspend fun getSearchedNews(country:String , searchQuery:String, page:Int) : Resource<APIResponse>

}