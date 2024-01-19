package com.vortex.android.vortexdictionary.fragments.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vortex.android.vortexdictionary.repository.BaseAppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferences: BaseAppPreferences
) : ViewModel() {

    var translationCounter = 0
    var wordCounter = 0
    var hasSubscription: Boolean = false

    init {
        getWordCounter()
        getTranslationCounter()
        getSubscriptionStatus()
    }


    private fun getTranslationCounter() = viewModelScope.launch {
        preferences.translationsViewedCounter.collectLatest {
            translationCounter = it
        }
    }

    fun setTranslationCounter() = viewModelScope.launch {
        preferences.setTranslationsViewedCounter(translationCounter)
    }

    private fun getWordCounter() = viewModelScope.launch {
        preferences.wordsAddedCounter.collectLatest {
            wordCounter = it
        }
    }

    fun setWordCounter() = viewModelScope.launch {
        preferences.setWordsAddedCounter(translationCounter)
    }

    private fun getSubscriptionStatus() = viewModelScope.launch {
        preferences.hasSubscription.collectLatest {
            hasSubscription = it
        }
    }

    fun setSubscriptionStatus(subscriptionStatus: Boolean) = viewModelScope.launch {
        preferences.setSubscription(subscriptionStatus)
    }
}