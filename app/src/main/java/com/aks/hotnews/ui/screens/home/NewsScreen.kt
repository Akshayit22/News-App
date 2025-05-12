package com.aks.hotnews.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.aks.hotnews.ui.components.news.NewsItem
import com.aks.hotnews.ui.components.news.SkeletonNewsItem


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(viewModel: NewsViewModel, navController: NavController) {
    val state = viewModel.state.value
    val page = viewModel.topNewsPage.value

    val pullRefreshState = rememberPullRefreshState(
        refreshing = viewModel.pullToRefreshState.value,
        onRefresh = { viewModel.topNewsPageRefresh() }
    )

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
                LazyColumn {
                    items(5) {
                        SkeletonNewsItem()
                    }
                }
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState)
            ) {
                LazyColumn {
                    items(newsList) { article ->
                        NewsItem(article, navController = navController)
                    }
                }
                PullRefreshIndicator(
                    refreshing = viewModel.pullToRefreshState.value,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }

//            LazyColumn {
//                items(newsList) { article ->
//                    NewsItem(article, navController = navController)
//                }
//            }
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
