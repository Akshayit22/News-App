package com.aks.hotnews.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNav() {

    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryFlow.collectAsStateWithLifecycle(
        null
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentBackStackEntry?.destination?.route in tabRoutes) {
                NavigatorBar(currentBackStackEntry,navController)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            MyNavGraph(navController = navController)
        }
    }
}


@Composable
fun NavigatorBar(currentBackStackEntry: NavBackStackEntry?, navController: NavController){

    var navBarState = when (currentBackStackEntry?.destination?.route) {
        Home::class.simpleName -> 0
        Search::class.simpleName -> 1
        Saved::class.simpleName -> 2
        Settings::class.simpleName -> 3
        else -> -1
    }

    NavigationBar {
        NavItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = navBarState == index,
                onClick = {
                    navBarState = index
                    navController.popBackStack()
                    when (index) {
                        0 -> navController.navigate(Home::class.simpleName!!)
                        1 -> navController.navigate(Search::class.simpleName!!)
                        2 -> navController.navigate(Saved::class.simpleName!!)
                        3 -> navController.navigate(Settings::class.simpleName!!)
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (navBarState == index) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title,
                        modifier = Modifier.size(28.dp)
                    )
                }, label = {
                    Text(text = item.title, fontSize = 12.sp)
                },
                alwaysShowLabel = false,
            )
        }
    }
}