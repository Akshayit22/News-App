package com.aks.hotnews.redux.news

import com.aks.hotnews.HotNewsApp
import com.aks.hotnews.data.db.BookmarkDao
import com.aks.hotnews.data.db.repository.BookmarkRepository
import com.aks.hotnews.redux.news.middelware.createBookmarkMiddleware
import com.aks.hotnews.redux.news.middelware.createNewsMiddleware
import com.aks.hotnews.redux.news.reducer.newsReducer
import com.aks.hotnews.redux.news.state.NewsState
import org.reduxkotlin.Reducer
import org.reduxkotlin.Store
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.createThreadSafeStore

data class AppState(
    val newsState: NewsState = NewsState()
)

val appReducer: Reducer<AppState> = { state, action ->
    state.copy(newsState = newsReducer(state.newsState, action))
}

fun StoreProvider(dao: BookmarkDao): Store<AppState> {
    return createThreadSafeStore(
        reducer = appReducer,
        preloadedState = AppState(),
        enhancer = applyMiddleware(createNewsMiddleware(), createBookmarkMiddleware(BookmarkRepository(dao)))
    )
}