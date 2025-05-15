package com.aks.hotnews.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aks.hotnews.data.model.news.News
import com.aks.hotnews.redux.news.AppState
import com.aks.hotnews.ui.screens.detail.DetailScreen
import com.aks.hotnews.ui.screens.home.HomeScreen
import com.aks.hotnews.ui.screens.saved.SavedScreen
import com.aks.hotnews.ui.screens.searchNews.SearchNews
import com.aks.hotnews.ui.screens.settings.SettingsScreen
import com.google.gson.Gson
import org.reduxkotlin.Store

@Composable
fun MyNavGraph(navController: NavHostController, store: Store<AppState>) {
    NavHost(navController = navController, startDestination = Saved::class.simpleName!!) {
        composable(Home::class.simpleName!!) {
            HomeScreen(navController, store)
        }

        composable(
            route = "detail/{newsJson}",
            arguments = listOf(navArgument("newsJson") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val jsonUser = backStackEntry.arguments?.getString("newsJson")
            val news = Gson().fromJson(jsonUser, News::class.java)
            DetailScreen(news, {navController.popBackStack()}, store)
        }

        composable(Search::class.simpleName!!) {
            SearchNews(navController,store)
        }
        composable(Settings::class.simpleName!!) {
            SettingsScreen(store)
        }
        composable(Saved::class.simpleName!!) {
            SavedScreen(store, navController)
        }
    }
}