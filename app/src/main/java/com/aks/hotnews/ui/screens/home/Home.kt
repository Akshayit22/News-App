package com.aks.hotnews.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.aks.hotnews.redux.news.AppState
import com.aks.hotnews.redux.news.actions.NewsAction
import com.aks.hotnews.redux.news.state.NewsState
//import com.aks.hotnews.utils.helper.NewsScreen
//import com.aks.hotnews.utils.helper.NewsViewModel
import org.reduxkotlin.Store

@Composable
fun Home(navController: NavController){
    Column (
        modifier = Modifier.fillMaxSize(),
    ){
        Text("Home")
//        LanguageDropdown(languages)
//
//        CountryDropdown(countries)

//        NewsScreen(NewsViewModel())

        NewsScreen()

    }
}