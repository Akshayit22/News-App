package com.aks.hotnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.aks.hotnews.redux.news.AppState
import com.aks.hotnews.redux.news.StoreProvider
import com.aks.hotnews.ui.navigation.BottomNav
import com.aks.hotnews.ui.theme.HotNewsTheme
import org.reduxkotlin.Store

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val dao = (application as HotNewsApp).database.bookmarkDao()
        val store: Store<AppState> = StoreProvider(dao)

        setContent {
            HotNewsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(WindowInsets.safeDrawing.asPaddingValues()),
                    ) {
                        BottomNav(store)
                    }
                }
            }
        }
    }
}
