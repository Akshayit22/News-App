package com.aks.hotnews.data.model.topnews

data class TopNews(
    val country: String,
    val language: String,
    val top_news: List<TopNew>
)