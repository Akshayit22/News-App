package com.aks.hotnews.data.model.news

data class NewsModel(
    val available: Int,
    val news: List<News>,
    val number: Int,
    val offset: Int
)