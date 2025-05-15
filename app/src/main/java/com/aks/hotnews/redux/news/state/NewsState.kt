package com.aks.hotnews.redux.news.state

import com.aks.hotnews.data.model.news.News
import com.aks.hotnews.data.model.news.NewsModel
import com.aks.hotnews.data.model.other.CountryCode
import com.aks.hotnews.data.model.other.LanguageCode
import com.aks.hotnews.data.model.topnews.TopNews

data class NewsState(
    val isLoading: Boolean = false,
    val topNews: TopNews? = null,
    val searchNews: NewsModel? = null,
    val error: String? = null,
    val isTopNewsLoaded: Boolean = false,
    val languageCode: LanguageCode?  = LanguageCode("English", "en"),
    val countryCode: CountryCode? = CountryCode("India", "in"),
    val topNewsPage: Int? = 0,
    val searchOffset: Int? = 0,
    val searchQuery: String = "",

    val bookmarkedNews: List<News> = emptyList()
)