package com.vortex.android.vortexdictionary.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class AppPreferences @Inject constructor (
    private val dataStore: DataStore<Preferences>
    ) : BaseAppPreferences {

    private val WORDS_ADDED_COUNTER_KEY = intPreferencesKey("wordsAddedCounter")
    private val TRANSLATIONS_VIEWED_COUNTER_KEY = intPreferencesKey("translationsViewedCounter")
    private val HAS_SUBSCRIPTION_KEY = booleanPreferencesKey("hasSubscription")

    override val wordsAddedCounter: Flow<Int> = dataStore.data.map {
        it[WORDS_ADDED_COUNTER_KEY] ?: 0
    }.distinctUntilChanged()

    override suspend fun setWordsAddedCounter(counter: Int) {
        dataStore.edit {
            it[WORDS_ADDED_COUNTER_KEY] = counter
        }
    }

    override val translationsViewedCounter: Flow<Int> = dataStore.data.map {
        it[TRANSLATIONS_VIEWED_COUNTER_KEY] ?: 0
    }

    override suspend fun setTranslationsViewedCounter(counter: Int) {
        dataStore.edit {
            it[TRANSLATIONS_VIEWED_COUNTER_KEY] = counter
        }
    }

    override val hasSubscription: Flow<Boolean> = dataStore.data.map {
        it[HAS_SUBSCRIPTION_KEY] ?: false
    }.distinctUntilChanged()

    override suspend fun setSubscription(hasSubscription: Boolean) {
        dataStore.edit {
            it[HAS_SUBSCRIPTION_KEY] = hasSubscription
        }
    }
}