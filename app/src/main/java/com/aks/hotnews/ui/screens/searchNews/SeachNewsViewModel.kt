package com.aks.hotnews.ui.screens.searchNews

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.aks.hotnews.redux.news.AppState
import com.aks.hotnews.redux.news.actions.NewsAction
import com.aks.hotnews.redux.news.state.NewsState
import org.reduxkotlin.Store

class SearchViewModel(val store: Store<AppState>) : ViewModel() {

    private val _state = mutableStateOf(store.state.newsState)
    val state: State<NewsState> = _state

    private val _pullToRefreshState = mutableStateOf(false)
    var pullToRefreshState: State<Boolean> = _pullToRefreshState

    init {
        store.subscribe {
            _state.value = store.state.newsState
        }
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

    fun setSearchQuery(query:String){
        store.dispatch(NewsAction.SetSearchQuery(query))
    }

}