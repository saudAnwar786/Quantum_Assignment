package com.priyam.quantumassignment.data.repository.dataSourceImpl

import com.priyam.quantumassignment.data.api.NewsAPIService
import com.priyam.quantumassignment.data.model.APIResponse
import com.priyam.quantumassignment.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private  val newsAPIService: NewsAPIService ,
):NewsRemoteDataSource {

    override suspend fun getTopHeadlines(country:String , page:Int): Response<APIResponse> {
        return newsAPIService.getTopHeadlines(country,page)
    }

    override suspend fun getSearchedNews(
        country: String,
        searchedQuery: String,
        page: Int
    ): Response<APIResponse> {
        return newsAPIService.getSearchedTopHeadlines(country,searchedQuery,page)
    }
}