package com.aks.hotnews.data.remote

import com.aks.hotnews.data.model.news.NewsModel
import com.aks.hotnews.data.model.topnews.TopNews
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET
    suspend fun getTopNews(
        @Query("api-key") apiKey: String,
        @Query("language") language: String,
        @Query("source-countries") sourceCountries: String,
        @Query("date") date: String? = null // 2025-05-01
    ): TopNews

    @GET
    suspend fun searchNews(
        @Query("api-key") apiKey: String,
        @Query("language") language: String,
        @Query("source-countries") sourceCountries: String? = null,
        @Query("offset") offset: Int,
        @Query("number") number: Int,
        @Query("text") text: String? = null,
        @Query("earliest-publish-date") earliestPublishDate: String? = null, //today, 2025-05-02
        @Query("sort") sort: String? = null, // publish-time
        @Query("sort-direction") sortDirection: String? = null, // ASC or DESC
    ): NewsModel

}