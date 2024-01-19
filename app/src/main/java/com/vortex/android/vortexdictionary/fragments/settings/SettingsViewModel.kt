package com.vortex.android.vortexdictionary.fragments.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vortex.android.vortexdictionary.repository.BaseAppPreferences
import com.vortex.android.vortexdictionary.repository.BaseWordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferences: BaseAppPreferences,
    private val repository: BaseWordRepository
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

    fun setTranslationCounter(counter: Int) = viewModelScope.launch {
        preferences.setTranslationsViewedCounter(counter)
    }

    private fun getWordCounter() = viewModelScope.launch {
        preferences.wordsAddedCounter.collectLatest {
            wordCounter = it
        }
    }

    fun setWordCounter(counter: Int) = viewModelScope.launch {
        preferences.setWordsAddedCounter(counter)
    }

    private fun getSubscriptionStatus() = viewModelScope.launch {
        preferences.hasSubscription.collectLatest {
            hasSubscription = it
        }
    }

    fun setSubscriptionStatus(subscriptionStatus: Boolean) = viewModelScope.launch {
        preferences.setSubscription(subscriptionStatus)
    }

    fun clearDatabase() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteDatabase()
    }
}