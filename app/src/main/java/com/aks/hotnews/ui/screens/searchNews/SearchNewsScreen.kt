package com.aks.hotnews.ui.screens.searchNews

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aks.hotnews.ui.components.news.NewsItem
import com.aks.hotnews.ui.components.news.SkeletonNewsItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchNewsScreen(navController: NavController, viewModel: SearchViewModel) {

    val state = viewModel.state.value
    val language = state.languageCode?.Code ?: "en"
    val country = state.countryCode?.Code ?: "in"
    var offset by remember { mutableStateOf(state.searchOffset?: 0) }

    val categoryList = listOf(
        "Technology", "Business", "Entertainment", "Health", "Global Economy",
        "Science", "Sports", "Stocks", "Crypto", "Politics", "World"
    )
    var selectedCategory by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }
    var isSearchExpanded by remember { mutableStateOf(false) }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = viewModel.pullToRefreshState.value,
        onRefresh = {
            viewModel.refreshSearchPage(offset + 1)
        }
    )

    LaunchedEffect(state.searchQuery, state.searchOffset) {
        if (state.searchNews == null) {
            Log.d("SearchNews", "Making API call")
            viewModel.fetchSearchNews(
                language = language,
                country = country,
                text = state.searchQuery,
                offset = state.searchOffset ?: 0,
                number = 30
            )
        } else {
            Log.d("SearchNews", "Search news already loaded")
        }
    }

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        if (isSearchExpanded) {
            OutlinedTextField(
                value = searchQuery,
                placeholder = {
                    Text("Search news...")
                },
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .height(52.dp)
                    .border(1.dp, Color.Gray, CircleShape)
                    .clip(CircleShape),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            isSearchExpanded = false
                            if (searchQuery != "") {
                                viewModel.setSearchQuery(searchQuery)
                            }
                        },
                        modifier = Modifier
                            .padding(end = 2.dp)
                            .background(
                                MaterialTheme.colorScheme.primary.copy(0.7f),
                                CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                ),
                shape = CircleShape,
                singleLine = true
            )
        } else {
            IconButton(
                onClick = { isSearchExpanded = true },
                modifier = Modifier.background(
                    MaterialTheme.colorScheme.onBackground.copy(0.2f),
                    CircleShape
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
        }

        categoryList.forEach { category ->
            Button(
                onClick = {
                    isSearchExpanded = false
                    selectedCategory = category
                    viewModel.setSearchQuery(category)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedCategory == category) MaterialTheme.colorScheme.primary.copy(
                        0.8f
                    )
                    else MaterialTheme.colorScheme.primary
                )
            ) {
                Text(text = category)
            }
        }
    }

    Spacer(modifier = Modifier.height(2.dp))


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

        state.searchNews != null -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState)
            ) {
                LazyColumn {
                    items(state.searchNews.news) { article ->
                        NewsItem(article, navController = navController)
                    }
                }
                PullRefreshIndicator(
                    refreshing = viewModel.pullToRefreshState.value,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }

        else -> {
            Text("No data loaded")
        }
    }


}

@Composable
fun FullWidthWithPeekCards() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(5) { rowIndex ->
            val listState = rememberLazyListState()
            LazyRow(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 8.dp),
                flingBehavior = rememberSnapFlingBehavior(listState)
            ) {
                items(5) {
                    SkeletonNewsItem(
                        modifier = Modifier
                            .fillParentMaxWidth(0.95f)
                    )
                }
            }
        }
    }
}