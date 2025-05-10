package com.aks.hotnews.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.aks.hotnews.data.model.news.News

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(news: News, onBack: () -> Unit = {}) {
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("News Details", fontSize = 16.sp, fontStyle = FontStyle.Italic) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Mark as favorite"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .verticalScroll(scrollState)
        ) {
            AsyncImage(
                model = news.image,
                contentDescription = news.title?:"",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = news.title?:"",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "By ${news.author ?: "Unknown"} • ${news.publish_date?:""}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                val cleanText = news.text//.replace("\\n", "\n").replace("\\\"", "\"")
                val paragraphs = cleanText.split("\\n")

                val annotatedString = buildAnnotatedString {
                    paragraphs.forEachIndexed { index, paragraph ->
                        withStyle(style = ParagraphStyle(lineHeight = 24.sp)) {
                            appendStyledParagraph(paragraph)
                        }
                    }
                }

                Text(
                    text = annotatedString,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


fun AnnotatedString.Builder.appendStyledParagraph(paragraph: String) {
    if (paragraph.isNullOrEmpty()) return
    var currentIndex = 0
    val regex = Regex("(['\"“”‘’])(.*?)\\1") // Matches both straight and curly quotes

    regex.findAll(paragraph).forEach { matchResult ->
        val matchStart = matchResult.range.first
        val matchEnd = matchResult.range.last + 1

        // Append text before the quoted part
        if (currentIndex < matchStart) {
            append(paragraph.substring(currentIndex, matchStart))
        }

        // Append quoted text in bold
        val quotedText = matchResult.groupValues[2]
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(quotedText)
        }

        currentIndex = matchEnd
    }

    // Append any remaining text after the last match
    if (currentIndex < paragraph.length) {
        append(paragraph.substring(currentIndex))
    }
}