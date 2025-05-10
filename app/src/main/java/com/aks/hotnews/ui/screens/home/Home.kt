package com.aks.hotnews.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aks.hotnews.redux.news.AppState
import org.reduxkotlin.Store

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, store: Store<AppState>) {

//    val data = newsList

    //NewsScreen(remember { NewsViewModel() },navController)

//    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//        Text(text = "Home", fontSize = 32.sp)
//
//        Button(onClick = {
//            navController.navigate("detail")
//        }) {
//            Text("Go to Detail")
//        }
//        //NewsScreen(NewsViewModel())
//    }

    val viewModel = remember { NewsViewModel(store) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Hot News", fontSize = 18.sp, fontStyle = FontStyle.Italic) },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.topNewsPageRefresh()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = "Mark as favorite"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(top = paddingValues.calculateTopPadding(), bottom = 100.dp)
        ) {
            NewsScreen(viewModel,navController)
        }
//        LazyColumn(
//            modifier = Modifier.padding(top = paddingValues.calculateTopPadding(), bottom = 100.dp)
//        ) {
//            items(data) { news ->
//                NewsItem(news,navController)
//            }
//        }
    }



}