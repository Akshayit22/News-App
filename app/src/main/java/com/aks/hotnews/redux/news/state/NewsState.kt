package com.aks.hotnews.redux.news.state

import com.aks.hotnews.data.model.news.NewsModel
import com.aks.hotnews.data.model.topnews.TopNews
import androidx.compose.runtime.getValue

data class NewsState(
    val isLoading: Boolean = false,
    val topNews: TopNews? = null,
    val searchNews: NewsModel? = null,
    val error: String? = null
)