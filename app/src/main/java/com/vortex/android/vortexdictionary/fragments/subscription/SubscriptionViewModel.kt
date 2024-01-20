package com.vortex.android.vortexdictionary.fragments.subscription

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vortex.android.vortexdictionary.repository.BaseAppPreferences
import com.vortex.android.vortexdictionary.repository.BaseWordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubscriptionViewModel @Inject constructor(
    private val repository: BaseWordRepository,
    private val preferences: BaseAppPreferences
    ) : ViewModel() {

    fun buySubscription() = viewModelScope.launch {
        preferences.setSubscription(true)
    }

    fun getSubscriptionPrice() = repository.getSubscriptionPrice()
}