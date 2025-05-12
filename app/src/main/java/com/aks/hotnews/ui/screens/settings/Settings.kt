package com.aks.hotnews.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.aks.hotnews.data.model.other.CountryCode
import com.aks.hotnews.data.model.other.LanguageCode
import com.aks.hotnews.data.model.other.countries
import com.aks.hotnews.data.model.other.languages
import com.aks.hotnews.redux.news.AppState
import com.aks.hotnews.ui.components.dropdown.CountryDropdown
import com.aks.hotnews.ui.components.dropdown.LanguageDropdown
import org.reduxkotlin.Store

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(store: Store<AppState>) {

    val viewModel = remember { SettingsViewModel(store) }
    val state = viewModel.state.value

    val selectedCountry = state.countryCode ?: CountryCode("India", "in")
    val selectedLanguage = state.languageCode ?: LanguageCode("English", "en")

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Settings", fontSize = 18.sp, fontStyle = FontStyle.Italic ) },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
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
            Text(text = "Settings", fontSize = 32.sp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                LanguageDropdown(
                    languages = languages,
                    selectedLanguage = selectedLanguage,
                    onLanguageSelected = { viewModel.setLanguage(it) },
                    modifier = Modifier.weight(0.9f)
                )

                CountryDropdown(
                    countries = countries,
                    selectedCountry = selectedCountry,
                    onCountrySelected = { viewModel.setCountry(it) },
                    modifier = Modifier.weight(1.1f)
                )
            }

        }
    }
}
