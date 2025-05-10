package com.aks.hotnews.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Search

@Serializable
object Settings

@Serializable
object Saved


data class NavItemState(
    val title:String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val NavItems = listOf<NavItemState>(
    NavItemState(
        "Top News",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    NavItemState(
        "Search News",
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search
    ),
    NavItemState(
        "Saved",
        selectedIcon = Icons.Filled.Star,
        unselectedIcon = Icons.Outlined.Star
    ),
    NavItemState(
        "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
)


val tabRoutes = listOf(
    Home::class.simpleName,
    Search::class.simpleName,
    Settings::class.simpleName,
    Saved::class.simpleName
)
