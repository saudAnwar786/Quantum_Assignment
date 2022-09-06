package com.priyam.quantumassignment.data.api


import com.priyam.quantumassignment.BuildConfig
import com.priyam.quantumassignment.data.model.APIResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface NewsAPIService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        country:String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apiKey:String = BuildConfig.API_KEY
    ): Response<APIResponse>

    @GET("v2/top-headlines")
    suspend fun getSearchedTopHeadlines(
        @Query("country")
        country:String,
        @Query("q")
        searchQuery:String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apiKey:String = BuildConfig.API_KEY
    ): Response<APIResponse>
}