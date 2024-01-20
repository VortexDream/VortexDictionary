package com.vortex.android.vortexdictionary.repository

import com.vortex.android.vortexdictionary.repository.model.Word
import kotlinx.coroutines.flow.Flow

interface BaseWordRepository {

    suspend fun getRandomWord(): Word?

    suspend fun addWord(word: Word)

    suspend fun getAllWords(): Flow<List<Word>>

    suspend fun searchDatabase(searchQuery: String): Flow<List<Word>>

    suspend fun deleteDatabase()

    fun getSubscriptionPrice(): String

    //Сортировка

    fun getItemsSortedByDateAscended(): Flow<List<Word>>

    fun getItemsSortedByDateDescended(): Flow<List<Word>>

    fun getItemsSortedByLatinAscended(): Flow<List<Word>>

    fun getItemsSortedByLatinDescended(): Flow<List<Word>>

    fun getItemsSortedByCyrillicAscended(): Flow<List<Word>>

    fun getItemsSortedByCyrillicDescended(): Flow<List<Word>>
}