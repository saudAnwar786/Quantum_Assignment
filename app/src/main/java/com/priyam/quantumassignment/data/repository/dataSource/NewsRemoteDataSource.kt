package com.priyam.quantumassignment.data.repository.dataSource

import com.priyam.quantumassignment.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country:String , page:Int):Response<APIResponse>
    suspend fun getSearchedNews(country:String ,searchedQuery: String ,page:Int):Response<APIResponse>
}