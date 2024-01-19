package com.vortex.android.vortexdictionary.fragments.newword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vortex.android.vortexdictionary.model.Word
import com.vortex.android.vortexdictionary.repository.BaseAppPreferences
import com.vortex.android.vortexdictionary.repository.BaseWordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewWordViewModel @Inject constructor(
    private val repository: BaseWordRepository,
    private val preferences: BaseAppPreferences
) : ViewModel() {

    fun addWord(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.addWord(word)
    }

    var wordCounter = 0

    init {
        getWordCounter()
    }

    private fun getWordCounter() = viewModelScope.launch {
        preferences.wordsAddedCounter.collectLatest {
            wordCounter = it
        }
    }

    fun setWordCounter() = viewModelScope.launch {
        preferences.setWordsAddedCounter(wordCounter)
    }

}