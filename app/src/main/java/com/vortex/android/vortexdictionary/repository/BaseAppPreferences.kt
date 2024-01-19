package com.vortex.android.vortexdictionary.repository

import kotlinx.coroutines.flow.Flow

interface BaseAppPreferences {

    val wordsAddedCounter: Flow<Int>
    suspend fun setWordsAddedCounter(counter: Int)

    val translationsViewedCounter: Flow<Int>
    suspend fun setTranslationsViewedCounter(counter: Int)

    val hasSubscription: Flow<Boolean>
    suspend fun setSubscription(hasSubscription: Boolean)

}