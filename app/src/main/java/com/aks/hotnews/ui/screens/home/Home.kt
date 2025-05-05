package com.aks.hotnews.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.aks.hotnews.data.model.other.countries
import com.aks.hotnews.data.model.other.languages
import com.aks.hotnews.ui.components.dropdown.CountryDropdown
import com.aks.hotnews.ui.components.dropdown.LanguageDropdown

@Composable
fun Home(navController: NavController){
    Column (
        modifier = Modifier.fillMaxSize(),
    ){
        Text("Home")
        LanguageDropdown(languages)

        CountryDropdown(countries)
    }
}




