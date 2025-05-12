package com.aks.hotnews.ui.components.dropdown

import androidx.compose.foundation.layout.fillMaxHeight
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
import com.aks.hotnews.data.model.other.CountryCode


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDropdown(
    countries: List<CountryCode>,
    modifier: Modifier = Modifier,
    selectedCountry: CountryCode,
    onCountrySelected: (CountryCode) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        TextField(
            value = selectedCountry.Country,
            onValueChange = {},
            readOnly = true,
            label = { Text("Select Country") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxHeight(0.6f)
        ) {
            countries.forEach { country ->
                DropdownMenuItem(
                    text = { Text("${country.Country} (${country.Code})") },
                    onClick = {
                        onCountrySelected(country)
                        expanded = false
                    }
                )
            }
        }
    }
}