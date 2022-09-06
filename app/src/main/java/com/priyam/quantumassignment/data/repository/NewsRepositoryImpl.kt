package com.priyam.quantumassignment.data.repository;

import com.priyam.quantumassignment.data.model.APIResponse
import com.priyam.quantumassignment.data.repository.dataSource.NewsRemoteDataSource
import com.priyam.quantumassignment.data.util.Resource
import com.priyam.quantumassignment.domain.repository.NewsRepository
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
): NewsRepository {

    private fun responseToResource(response: Response<APIResponse>):Resource<APIResponse>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getNewsHeadlines(country:String , page:Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(country,page))
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getSearchedNews(country,searchQuery , page))
    }


}
