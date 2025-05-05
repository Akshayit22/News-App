package com.aks.hotnews.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.aks.hotnews.BuildConfig
import com.aks.hotnews.data.model.news.NewsModel
import com.aks.hotnews.data.model.topnews.TopNews
import com.aks.hotnews.data.remote.RetrofitInstance
import com.aks.hotnews.utils.network.NetworkResponse

class NewsRepository {
    private val apiService = RetrofitInstance.api

    suspend fun getTopNews(
        language: String,
        country: String,
    ): TopNews {
        return apiService.getTopNews(
            apiKey = BuildConfig.API_KEY,
            language = language,
            sourceCountries = country,
        )
    }

    suspend fun searchNews(
        language: String,
        offset: Int,
        number: Int,
        country: String? = null,
        text: String? = null,
        earliestPublishDate: String? = null,
        sort: String? = null,
        sortDirection: String? = null
    ): NewsModel {

        val response = apiService.searchNews(
            apiKey = BuildConfig.API_KEY,
            language = language,
            sourceCountries = country,
            offset = offset,
            number = number,
            text = text,
            earliestPublishDate = earliestPublishDate,
            sort = sort,
            sortDirection = sortDirection
        )
        Log.d("Response: ", response.toString())
        return response
    }
}