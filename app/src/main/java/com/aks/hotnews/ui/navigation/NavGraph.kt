package com.aks.hotnews.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aks.hotnews.ui.screens.detail.Detail
import com.aks.hotnews.ui.screens.home.Home

@Composable
fun NavigationGraph(){
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Home.route){

        composable(route = Screen.Home.route){
            Home(navController)
        }

        composable(route = Screen.Detail.route){
            Detail(navController)
        }

    }
}