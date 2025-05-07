package com.aks.hotnews.redux.news

import com.aks.hotnews.redux.news.reducer.newsReducer
import com.aks.hotnews.redux.news.state.NewsState
import org.reduxkotlin.Reducer

data class AppState(
    val newsState: NewsState = NewsState()
)

val appReducer: Reducer<AppState> = { state, action ->
    state.copy(newsState = newsReducer(state.newsState, action))
}