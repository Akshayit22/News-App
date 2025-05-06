package com.aks.hotnews.redux.news.middelware

import com.aks.hotnews.data.repository.NewsRepository
import com.aks.hotnews.redux.news.AppState
import com.aks.hotnews.redux.news.actions.NewsAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.reduxkotlin.Middleware

fun createNewsMiddleware(repository: NewsRepository): Middleware<AppState> = { store ->
    { next ->
        { action ->
            when (action) {
                is NewsAction.FetchTopNews -> {
                    store.dispatch(NewsAction.Loading)
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val result = repository.getTopNews(
                                language = action.language,
                                country = action.country,
                            )
                            //println("Response: ${result}")
                            store.dispatch(NewsAction.TopNewsSuccess(result))
                        } catch (e: Exception) {
                            store.dispatch(NewsAction.Error(e.message ?: "Unknown error"))
                        }
                    }
                }

                is NewsAction.FetchSearchNews -> {
                    store.dispatch(NewsAction.Loading)
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val result = repository.searchNews(
                                language = action.language,
                                offset = action.offset,
                                number = action.number,
                                country = action.country,
                                text = action.text,
                                earliestPublishDate = action.earliestPublishDate,
                                sort = action.sort,
                                sortDirection = action.sortDirection
                            )
                            //println("Response: ${result}")
                            store.dispatch(NewsAction.SearchNewsSuccess(result))
                        } catch (e: Exception) {
                            store.dispatch(NewsAction.Error(e.message ?: "Unknown error"))
                        }
                    }
                }

                else -> next(action)
            }
        }
    }
}