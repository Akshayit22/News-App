package com.aks.hotnews.ui.screens.searchNews

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.hotnews.redux.news.AppState
import com.aks.hotnews.redux.news.actions.NewsAction
import com.aks.hotnews.redux.news.state.NewsState
import kotlinx.coroutines.launch
import org.reduxkotlin.Store

class SearchViewModel(val store: Store<AppState>) : ViewModel() {

    private val _state = mutableStateOf(store.state.newsState)
    val state: State<NewsState> = _state
    val DEFAULT_PAGE_SIZE = 30

    private val _pullToRefreshState = mutableStateOf(false)
    var pullToRefreshState: State<Boolean> = _pullToRefreshState

    private val _searchOffset = mutableIntStateOf(store.state.newsState.searchOffset ?: 0)
    val searchOffset: State<Int> = _searchOffset

    init {
        store.subscribe {
            val newState = store.state.newsState
            _state.value = newState
            _searchOffset.intValue = newState.searchOffset ?: 0
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
        store.dispatch(NewsAction.SetSearchOffset(0))
    }

    fun refreshSearchPage(){
        _pullToRefreshState.value = true

        viewModelScope.launch {
            val newOffset = _searchOffset.intValue + DEFAULT_PAGE_SIZE
            store.dispatch(NewsAction.SetSearchOffset(newOffset))
            _pullToRefreshState.value = false
            Log.d("SearchNews", "Making API call")
            fetchSearchNews(
                language = state.value.languageCode?.Code ?: "en",
                country = state.value.countryCode?.Code?: "in",
                text = state.value.searchQuery,
                offset = state.value.searchOffset!!.toInt(),
                number = DEFAULT_PAGE_SIZE
            )
        }
    }
}