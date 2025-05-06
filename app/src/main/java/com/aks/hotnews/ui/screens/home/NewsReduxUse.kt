package com.aks.hotnews.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.aks.hotnews.redux.news.StoreProvider
import com.aks.hotnews.redux.news.actions.NewsAction
import com.aks.hotnews.redux.news.state.NewsState

class NewsViewModel : ViewModel() {
    private val store = StoreProvider.store

    private val _state = mutableStateOf(store.state.newsState)
    val state: State<NewsState> = _state

    init {
        store.subscribe {
            _state.value = store.state.newsState
        }
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
}


@Composable
fun NewsScreen(viewModel: NewsViewModel = remember { NewsViewModel() }) {
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        // Example: Fetch top news on screen load
        //viewModel.fetchTopNews(language = "en", country = "us")
        // OR for search page:
        viewModel.fetchSearchNews(language = "en", offset = 1, number = 10, text = "india+pakistan")
    }

    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${state.error}")
            }
        }

//        state.topNews != null -> {
//            LazyColumn {
//                items(state.topNews.top_news) { article ->
//                    Text(article.news[0].title ?: "No title", modifier = Modifier.run { padding(8.dp) })
//                }
//            }
//        }

        state.searchNews != null -> {
            LazyColumn {
                items(state.searchNews.news) { article ->
                    Text(article.title, modifier = Modifier.padding(8.dp))
                }
            }
        }

        else -> {
                Text("No data loaded")
        }
    }
}