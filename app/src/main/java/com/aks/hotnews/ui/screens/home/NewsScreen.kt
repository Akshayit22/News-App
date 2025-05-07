package com.aks.hotnews.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aks.hotnews.data.model.news.News
import com.aks.hotnews.data.model.topnews.TopNew
import com.aks.hotnews.ui.components.news.NewsItem


@Composable
fun NewsScreen(viewModel: NewsViewModel, navController: NavController) {
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        // Example: Fetch top news on screen load
        if (state.topNews == null) {
            Log.d("NewsScreen", "Making api call")
            viewModel.fetchTopNews(language = "en", country = "in")
        } else {
            Log.d("NewsScreen", "Top news already loaded")
        }
        // OR for search page:
//        if(state.searchNews == null){
//            viewModel.fetchSearchNews(language = "en", offset = 1, number = 50, text = "india+pakistan")
//        }
    }

    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (state.error.contains("HTTP 402")) {
                    Text("API Limit Reached, please try again later...")
                } else {
                    Text("Error: ${state.error}")
                }

            }
        }

        state.topNews != null -> {
            LazyColumn(
                modifier = Modifier.padding(bottom = 100.dp)
            ) {
                val Pages = state.topNews.top_news[0].news.size //news array
                val x = state.topNews.top_news[0].news[1] // news

                items(state.topNews.top_news) { article ->
                    NewsItem(article.news[0], navController = navController)
                }
            }
        }

//        state.searchNews != null -> {
//            LazyColumn(
//                modifier = Modifier.padding(bottom = 100.dp)
//            ) {
//                items(state.searchNews.news) { article ->
//                    NewsItem(article, navController = navController)
//                }
//            }
//        }

        else -> {
            Text("No data loaded")
        }
    }
}