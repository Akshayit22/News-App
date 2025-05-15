package com.aks.hotnews.ui.screens.saved

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aks.hotnews.redux.news.AppState
import com.aks.hotnews.ui.components.news.NewsItem
import org.reduxkotlin.Store

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedScreen(store: Store<AppState>, navController: NavController) {

    val viewModel = BookmarkViewModel(store)
    val bookmarks = viewModel.state.value.bookmarkedNews


    LaunchedEffect(Unit) {
        viewModel.loadBookmarksFromDB()
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Hot News", fontSize = 18.sp, fontStyle = FontStyle.Italic ) },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(top = paddingValues.calculateTopPadding(), bottom = 100.dp)
        ) {
            if (bookmarks.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No bookmarks yet")
                }
            } else {
                LazyColumn {
                    items(bookmarks) { article ->
                        NewsItem(article, navController = navController)
                    }
                }
            }

        }
    }
}
