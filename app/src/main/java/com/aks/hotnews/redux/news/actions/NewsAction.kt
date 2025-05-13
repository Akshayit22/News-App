package com.aks.hotnews.redux.news.actions

import com.aks.hotnews.data.model.news.NewsModel
import com.aks.hotnews.data.model.other.CountryCode
import com.aks.hotnews.data.model.other.LanguageCode
import com.aks.hotnews.data.model.topnews.TopNews

sealed class NewsAction {
    object Loading : NewsAction()
    data class TopNewsSuccess(val data: TopNews) : NewsAction()
    data class SearchNewsSuccess(val data: NewsModel) : NewsAction()
    data class Error(val message: String) : NewsAction()
    data class SetLanguage(val languageCode: LanguageCode) : NewsAction()
    data class SetCountry(val countryCode: CountryCode) : NewsAction()
    data class SetTopNewsPage(val pageValue: Int) : NewsAction()
    data class SetSearchOffset(val offset: Int) : NewsAction()
    data class SetSearchQuery(val query: String) : NewsAction()

    data class FetchTopNews(
        val language: String,
        val country: String,
        val date: String? = null
    ) : NewsAction()

    data class FetchSearchNews(
        val language: String,
        val offset: Int,
        val number: Int,
        val country: String? = null,
        val text: String? = null,
        val earliestPublishDate: String? = null,
        val sort: String? = null,
        val sortDirection: String? = null
    ) : NewsAction()
}