package com.aks.hotnews.ui.components.dropdown

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.aks.hotnews.data.model.other.LanguageCode
import kotlin.collections.forEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageDropdown(languages: List<LanguageCode>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf(languages[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedLanguage.Language,
            onValueChange = {},
            readOnly = true,
            label = { Text("Select Language") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            languages.forEach { language ->
                DropdownMenuItem(
                    text = { Text("${language.Language} (${language.Code})") },
                    onClick = {
                        selectedLanguage = language
                        expanded = false
                    }
                )
            }
        }
    }
}