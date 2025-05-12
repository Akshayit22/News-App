package com.aks.hotnews.ui.screens.searchNews

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aks.hotnews.ui.components.news.SkeletonNewsItem

@Composable
fun SearchNewsScreen() {
    val categoryList = listOf(
        "General", "Technology", "Business", "Entertainment", "Health",
        "Science", "Sports", "STOCK", "Crypto", "Politics", "World", "India"
    )
    var selectedCategory by remember { mutableStateOf("") }

    var searchQuery by remember { mutableStateOf("") }
    var isSearchExpanded by remember { mutableStateOf(false) }

    Column{

        FlowRow(
            modifier = Modifier
                .fillMaxWidth().padding( horizontal = 8.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if(isSearchExpanded){
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .height(52.dp)
                        .border(1.dp, Color.Gray, CircleShape)
                        .clip(CircleShape),
                    trailingIcon = {
                        IconButton(
                            onClick = { isSearchExpanded = false },
                            modifier = Modifier.padding(end = 2.dp).background(MaterialTheme.colorScheme.primary.copy(0.7f), CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search"
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                    ),
                    shape = CircleShape,
                    singleLine = true
                )
            }else{
                IconButton(
                    onClick = { isSearchExpanded = true },
                    modifier = Modifier.background(MaterialTheme.colorScheme.onBackground.copy(0.2f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            }

            categoryList.forEach { category ->
                Button(
                    onClick = { selectedCategory = category },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedCategory == category) MaterialTheme.colorScheme.primary.copy(0.8f)
                        else MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = category)
                }
            }
        }

        Spacer(modifier = Modifier.height(2.dp))

//        LazyColumn {
//            items(5) {
//                SkeletonNewsItem()
//            }
//        }
    }
}