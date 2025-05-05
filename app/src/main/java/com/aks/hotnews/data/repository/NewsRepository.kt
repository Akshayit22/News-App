package com.aks.hotnews.data.repository

import com.aks.hotnews.BuildConfig
import com.aks.hotnews.data.model.news.NewsModel
import com.aks.hotnews.data.model.topnews.TopNews
import com.aks.hotnews.data.remote.RetrofitInstance
import com.aks.hotnews.utils.network.NetworkResponse

class NewsRepository {
    private val apiService = RetrofitInstance.api

    suspend fun getTopNews(): NetworkResponse<TopNews> {
        return try {
            val response = apiService.getTopNews(
                apiKey = BuildConfig.API_KEY,
                language = "en",
                sourceCountries = "us"
            )
            NetworkResponse.Success(response)
        } catch (e: Exception) {
            NetworkResponse.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }

    suspend fun getSearchNews(): NetworkResponse<NewsModel> {
        return try {
            val response = apiService.searchNews(
                apiKey = BuildConfig.API_KEY,
                language = "en",
                offset = 0,
                number = 5
            )
            NetworkResponse.Success(response)
        } catch (e: Exception) {
            NetworkResponse.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }
}