package com.aks.hotnews.ui.screens.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.aks.hotnews.data.model.other.CountryCode
import com.aks.hotnews.data.model.other.LanguageCode
import com.aks.hotnews.redux.news.AppState
import com.aks.hotnews.redux.news.actions.NewsAction
import com.aks.hotnews.redux.news.state.NewsState
import org.reduxkotlin.Store

class SettingsViewModel(val store: Store<AppState>) : ViewModel() {

    private val _state = mutableStateOf(store.state.newsState)
    val state: State<NewsState> = _state

    init {
        store.subscribe {
            _state.value = store.state.newsState
        }
    }

    fun setLanguage(language: LanguageCode) {
        store.dispatch(
            NewsAction.SetLanguage(language)
        )
    }

    fun setCountry(country: CountryCode) {
        store.dispatch(
            NewsAction.SetCountry(country)
        )
    }
}