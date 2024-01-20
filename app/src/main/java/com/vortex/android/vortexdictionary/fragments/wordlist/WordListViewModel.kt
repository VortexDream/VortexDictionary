package com.vortex.android.vortexdictionary.fragments.wordlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vortex.android.vortexdictionary.repository.model.Word
import com.vortex.android.vortexdictionary.repository.BaseWordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordListViewModel @Inject constructor(
    private val repository: BaseWordRepository
) : ViewModel() {

    private val _wordList = MutableStateFlow<List<Word>>(emptyList())
    val wordList: StateFlow<List<Word>>
        get() = _wordList.asStateFlow()

    fun getWordList() = viewModelScope.launch {
        repository.getAllWords().collect() {
            _wordList.value = it
        }
    }

    fun searchDatabase(searchQuery: String) = viewModelScope.launch {
        repository.searchDatabase(searchQuery).collect() {
            _wordList.value = it
        }
    }

    //Сортировка

    fun getItemsSortedByDateAscended() = viewModelScope.launch {
        repository.getItemsSortedByDateAscended().collect() {
            _wordList.value = it
        }
    }

    fun getItemsSortedByDateDescended() = viewModelScope.launch {
        repository.getItemsSortedByDateDescended().collect() {
            _wordList.value = it
        }
    }

    fun getItemsSortedByLatinAscended() = viewModelScope.launch {
        repository.getItemsSortedByLatinAscended().collect() {
            _wordList.value = it
        }
    }

    fun getItemsSortedByLatinDescended() = viewModelScope.launch {
        repository.getItemsSortedByLatinDescended().collect() {
            _wordList.value = it
        }
    }

    fun getItemsSortedByCyrillicAscended() = viewModelScope.launch {
        repository.getItemsSortedByCyrillicAscended().collect() {
            _wordList.value = it
        }
    }

    fun getItemsSortedByCyrillicDescended() = viewModelScope.launch {
        repository.getItemsSortedByCyrillicDescended().collect() {
            _wordList.value = it
        }
    }
}