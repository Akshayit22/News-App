package com.aks.hotnews.ui.screens.detail

import androidx.lifecycle.ViewModel
import com.aks.hotnews.data.model.news.News
import com.aks.hotnews.redux.news.AppState
import com.aks.hotnews.redux.news.actions.NewsAction
import com.aks.hotnews.redux.news.state.NewsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.reduxkotlin.Store


class DetailViewModel(
    private val store: Store<AppState>,
) : ViewModel() {

    private val _state = MutableStateFlow(store.state.newsState)
    val state: StateFlow<NewsState> = _state

    init {
        store.subscribe {
            _state.value = store.state.newsState
        }
    }

    fun toggleBookmark(article: News) {
        store.dispatch(NewsAction.ToggleBookmark(article))
    }
}