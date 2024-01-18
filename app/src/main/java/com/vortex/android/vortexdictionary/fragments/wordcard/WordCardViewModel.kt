package com.vortex.android.vortexdictionary.fragments.wordcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vortex.android.vortexdictionary.model.Word
import com.vortex.android.vortexdictionary.repository.BaseWordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordCardViewModel @Inject constructor(
    private val repository: BaseWordRepository
) : ViewModel() {

    private val _currentWord = MutableStateFlow<Word?>(null)
    val currentWord : StateFlow<Word?>
        get() = _currentWord.asStateFlow()

    init {
        getRandomWord()
    }

    fun getRandomWord() = viewModelScope.launch(Dispatchers.IO) {
        repository.getRandomWord()?.let {
            _currentWord.value = it
        }
    }

}