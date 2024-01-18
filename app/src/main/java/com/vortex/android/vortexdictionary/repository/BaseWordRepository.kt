package com.vortex.android.vortexdictionary.repository

import com.vortex.android.vortexdictionary.model.Word

interface BaseWordRepository {

    suspend fun getRandomWord(): Word?

    suspend fun addWord(word: Word)

}