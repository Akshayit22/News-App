package com.aks.hotnews.redux.news.reducer

import com.aks.hotnews.redux.news.actions.NewsAction
import com.aks.hotnews.redux.news.state.NewsState
import org.reduxkotlin.Reducer

val newsReducer: Reducer<NewsState> = { state, action ->
    when (action) {
        is NewsAction.Loading -> state.copy(isLoading = true, error = null)
        is NewsAction.TopNewsSuccess -> state.copy(isLoading = false, topNews = action.data, error = null)
        is NewsAction.SearchNewsSuccess -> state.copy(isLoading = false, searchNews = action.data, error = null)
        is NewsAction.Error -> state.copy(isLoading = false, error = action.message)
        is NewsAction.SetLanguage -> state.copy(languageCode = action.languageCode, topNews = null, searchNews = null)
        is NewsAction.SetCountry -> state.copy(countryCode = action.countryCode, topNews = null, searchNews = null)
        is NewsAction.SetTopNewsPage -> state.copy(topNewsPage = action.pageValue)
        is NewsAction.SetSearchQuery -> state.copy(searchQuery = action.query, searchNews = null)
        else -> state
    }
}