package com.aks.hotnews.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.aks.hotnews.redux.news.AppState
import com.aks.hotnews.redux.news.actions.NewsAction
import com.aks.hotnews.redux.news.state.NewsState
import org.reduxkotlin.Store

class NewsViewModel(val store: Store<AppState>) : ViewModel() {

    private val _state = mutableStateOf(store.state.newsState)
    val state: State<NewsState> = _state

    private val _topNewsPage = mutableIntStateOf(0)
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

    fun topNewsPageRefresh(){
        val maxPages = _state.value.topNews!!.top_news.size - 1
        if (_topNewsPage.intValue < maxPages) {
            _topNewsPage.intValue += 1
        } else {
            _topNewsPage.intValue = (0 until maxPages).random()
        }
    }
}