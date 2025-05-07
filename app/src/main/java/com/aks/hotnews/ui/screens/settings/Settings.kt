package com.aks.hotnews.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.aks.hotnews.data.model.other.countries
import com.aks.hotnews.data.model.other.languages
import com.aks.hotnews.ui.components.dropdown.CountryDropdown
import com.aks.hotnews.ui.components.dropdown.LanguageDropdown

@Composable
fun SettingsScreen() {
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = "Settings", fontSize = 32.sp)
        LanguageDropdown(languages)

        CountryDropdown(countries)
    }
}
