package com.aks.hotnews.data.remote

import com.aks.hotnews.data.model.news.NewsModel
import com.aks.hotnews.data.model.topnews.TopNews
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/top-news")
    suspend fun getTopNews(
        @Query("api-key") apiKey: String,
        @Query("language") language: String = "en",
        @Query("source-country") sourceCountries: String = "us",
        @Query("date") date: String? = null // 2025-05-01
    ): TopNews

    @GET("/search-news")
    suspend fun searchNews(
        @Query("api-key") apiKey: String,
        @Query("language") language: String = "en",
        @Query("source-countries") sourceCountries: String? = "us",
        @Query("offset") offset: Int = 0,
        @Query("number") number: Int = 10,
        @Query("text") text: String? = null,
        @Query("earliest-publish-date") earliestPublishDate: String? = null, //today, 2025-05-02
        @Query("sort") sort: String? = "publish-time", // publish-time
        @Query("sort-direction") sortDirection: String? = "DESC", // ASC or DESC
    ): NewsModel
}