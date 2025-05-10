package com.aks.hotnews.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.aks.hotnews.ui.components.news.NewsItem


@Composable
fun NewsScreen(viewModel: NewsViewModel, navController: NavController) {
    val state = viewModel.state.value
    val page = viewModel.topNewsPage.value

    LaunchedEffect(Unit) {
        if (state.topNews == null) {
            Log.d("NewsScreen", "Making API call")
            viewModel.fetchTopNews(language = "en", country = "in")
        } else {
            Log.d("NewsScreen", "Top news already loaded")
        }
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
            val newsList = state.topNews.top_news[page].news

            LazyColumn {
                items(newsList) { article ->
                    NewsItem(article, navController = navController)
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
