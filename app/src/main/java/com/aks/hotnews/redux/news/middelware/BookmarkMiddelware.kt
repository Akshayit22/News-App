package com.aks.hotnews.redux.news.middelware

import android.util.Log
import com.aks.hotnews.data.db.repository.BookmarkRepository
import com.aks.hotnews.redux.news.AppState
import com.aks.hotnews.redux.news.actions.NewsAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.reduxkotlin.Middleware

fun createBookmarkMiddleware(repository: BookmarkRepository): Middleware<AppState> = { store ->
    { next ->
        { action ->
            when (action) {
                is NewsAction.ToggleBookmark -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val isBookmarked = repository.isBookmarked(action.article.id)
                            if (isBookmarked) {
                                repository.remove(action.article)
                            } else {
                                repository.add(action.article)
                            }
                            val updated = repository.getAll()
                            store.dispatch(NewsAction.SetBookmarks(updated))
                        } catch (e: Exception) {
                            Log.d("BookmarkMiddleware", "Error in ToggleBookmark: ${e.message}")
                        }
                    }
                }

                is NewsAction.LoadBookmarks -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val bookmarks = repository.getAll()
                            store.dispatch(NewsAction.SetBookmarks(bookmarks))
                        } catch (e: Exception) {
                            Log.d("BookmarkMiddleware", "Error in LoadBookmarks: ${e.message}")
                        }
                    }
                }

                else -> next(action)
            }
        }
    }
}