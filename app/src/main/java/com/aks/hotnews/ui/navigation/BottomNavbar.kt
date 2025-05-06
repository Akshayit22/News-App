package com.aks.hotnews.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNav() {

    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryFlow.collectAsStateWithLifecycle(
        null
    )

    var navBarState = when (currentBackStackEntry?.destination?.route) {
        Home::class.simpleName -> 0
        Inbox::class.simpleName -> 1
        Account::class.simpleName -> 2
        else -> -1
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentBackStackEntry?.destination?.route in tabRoutes) {
                NavigationBar {
                    NavItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = navBarState == index,
                            onClick = {
                                navBarState = index
                                navController.popBackStack()
                                when (index) {
                                    0 -> navController.navigate(Home::class.simpleName!!)
                                    1 -> navController.navigate(Inbox::class.simpleName!!)
                                    2 -> navController.navigate(Account::class.simpleName!!)
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (navBarState == index) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.title,
                                )
                            }, label = {
                                Text(text = item.title)
                            }
                        )
                    }
                }
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