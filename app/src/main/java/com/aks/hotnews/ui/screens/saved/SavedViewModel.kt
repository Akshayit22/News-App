package com.aks.hotnews.ui.screens.saved

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.aks.hotnews.redux.news.AppState
import com.aks.hotnews.redux.news.actions.NewsAction
import com.aks.hotnews.redux.news.state.NewsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.reduxkotlin.Store

class BookmarkViewModel(private val store: Store<AppState>) : ViewModel() {

    private val _state = mutableStateOf(store.state.newsState)
    val state: State<NewsState> = _state

    init {
        store.subscribe {
            _state.value = store.state.newsState
        }
    }

    fun loadBookmarksFromDB() {
        CoroutineScope(Dispatchers.IO).launch {
            store.dispatch(NewsAction.LoadBookmarks)
        }
    }
}