package com.aks.hotnews.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aks.hotnews.ui.screens.detail.DetailScreen
import com.aks.hotnews.ui.screens.home.HomeScreen
import com.aks.hotnews.ui.screens.searchNews.SearchNewsScreens
import com.aks.hotnews.ui.screens.settings.SettingsScreen

@Composable
fun MyNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Home::class.simpleName!!) {
        composable(Home::class.simpleName!!) {
            HomeScreen(navController)
        }
        composable(Search::class.simpleName!!) {
            SearchNewsScreens()
        }
        composable(Settings::class.simpleName!!) {
            SettingsScreen()
        }
        composable("detail") {
            DetailScreen()
        }

        composable(Saved::class.simpleName!!) {
            SavedScreen()
        }
    }
}

@Composable
fun SavedScreen(){
    Text("SavedScreen")
}