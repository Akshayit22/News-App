package com.aks.hotnews.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.hotnews.redux.news.AppState
import com.aks.hotnews.redux.news.actions.NewsAction
import com.aks.hotnews.redux.news.state.NewsState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.reduxkotlin.Store

class NewsViewModel(val store: Store<AppState>) : ViewModel() {

    private val _state = mutableStateOf(store.state.newsState)
    val state: State<NewsState> = _state

    private val _topNewsPage = mutableIntStateOf(state.value.topNewsPage!!.toInt())
    private val _pullToRefreshState = mutableStateOf(false)

    var pullToRefreshState: State<Boolean> = _pullToRefreshState
    var topNewsPage: State<Int> = _topNewsPage


    init {
        store.subscribe {
            _state.value = store.state.newsState
        }
//        if (store.state.newsState.topNews == null) {
//            fetchTopNews(language = "en", country = "in")
//            Log.d("NewsScreen", "Api call made")
//        }else{
//            Log.d("NewsScreen", "Top news already loaded")
//        }
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

    fun topNewsPageRefresh(){
        _pullToRefreshState.value = true

        viewModelScope.launch {
            delay(500) // Delay added here

            val maxPages = _state.value.topNews!!.top_news.size - 1
            if (_topNewsPage.intValue < maxPages) {
                _topNewsPage.intValue += 1
            } else {
                _topNewsPage.intValue = (0 until maxPages).random()
            }
            store.dispatch(NewsAction.SetTopNewsPage(_topNewsPage.intValue))
            _pullToRefreshState.value = false
        }
    }
}