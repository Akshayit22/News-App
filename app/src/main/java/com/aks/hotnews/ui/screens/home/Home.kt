package com.aks.hotnews.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Home", fontSize = 32.sp)

        Button(onClick = {
            navController.navigate("detail")
        }) {
            Text("Go to Detail")
        }
    }
//        LanguageDropdown(languages)
//
//        CountryDropdown(countries)

//        NewsScreen(NewsViewModel())

//        NewsScreen()

}