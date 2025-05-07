package com.aks.hotnews.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.hotnews.data.repository.NewsRepository
import com.aks.hotnews.redux.news.AppState
import com.aks.hotnews.redux.news.actions.NewsAction
import com.aks.hotnews.redux.news.appReducer
import com.aks.hotnews.redux.news.middelware.createNewsMiddleware
import com.aks.hotnews.redux.news.state.NewsState
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.createThreadSafeStore

class NewsViewModel : ViewModel() {

    private val store = createThreadSafeStore(
        reducer = appReducer,
        preloadedState = AppState(),
        enhancer = applyMiddleware(createNewsMiddleware(NewsRepository(), viewModelScope))
    )

    private val _state = mutableStateOf(store.state.newsState)
    val state: State<NewsState> = _state

    init {
        store.subscribe {
            _state.value = store.state.newsState
        }
    }

    fun fetchTopNews(language: String, country: String, date: String? = null) {
        store.dispatch(
            NewsAction.FetchTopNews(
                language = language,
                country = country,
                date = date
            )
        )
    }

    fun fetchSearchNews(
        language: String,
        offset: Int,
        number: Int,
        country: String? = null,
        text: String? = null,
        earliestPublishDate: String? = null,
        sort: String? = null,
        sortDirection: String? = null
    ) {
        store.dispatch(
            NewsAction.FetchSearchNews(
                language = language,
                offset = offset,
                number = number,
                country = country,
                text = text,
                earliestPublishDate = earliestPublishDate,
                sort = sort,
                sortDirection = sortDirection
            )
        )
    }
}