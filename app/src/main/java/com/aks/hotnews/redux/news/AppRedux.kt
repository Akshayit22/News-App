package com.aks.hotnews.redux.news

import com.aks.hotnews.data.repository.NewsRepository
import com.aks.hotnews.redux.news.middelware.createNewsMiddleware
import com.aks.hotnews.redux.news.reducer.newsReducer
import com.aks.hotnews.redux.news.state.NewsState
import org.reduxkotlin.Reducer
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.createThreadSafeStore

data class AppState(
    val newsState: NewsState = NewsState()
)

val appReducer: Reducer<AppState> = { state, action ->
    state.copy(newsState = newsReducer(state.newsState, action))
}

object StoreProvider {
    val store = createThreadSafeStore(
        reducer = appReducer,
        preloadedState = AppState(),
        enhancer = applyMiddleware(createNewsMiddleware(NewsRepository()))
    )
}