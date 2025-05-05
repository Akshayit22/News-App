package com.aks.hotnews.utils.helper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.hotnews.data.model.news.NewsModel
import com.aks.hotnews.data.repository.NewsRepository
import kotlinx.coroutines.launch
import android.util.Log

data class NewsUiState(
    val isLoading: Boolean = false,
    val news: NewsModel? = null,
    val error: String? = null
)

class NewsViewModelUtills(
    private val repository: NewsRepository = NewsRepository()
) : ViewModel() {

    var newsState by mutableStateOf(NewsUiState())
        private set

    fun loadNews() {
        viewModelScope.launch {
            Log.d("NewsViewModel", "Starting news fetch...")
            newsState = newsState.copy(isLoading = true, error = null)

            try {
                val response = repository.searchNews(
                    language = "en",
                    offset = 0,
                    number = 5,
                    text = "India+pakistan",
                    sortDirection = "ASC"
                )
//                val response = repository.getTopNews(
//                    language = "ta",
//                    country = "in"
//                )
                newsState = newsState.copy(isLoading = false, news = response)
                Log.d("NewsViewModel", "News fetch success: $response")

            } catch (e: Exception) {
                Log.e("NewsViewModel", "News fetch error: ${e.message}", e)
                newsState = newsState.copy(isLoading = false, error = e.message)
            }
        }
    }
}