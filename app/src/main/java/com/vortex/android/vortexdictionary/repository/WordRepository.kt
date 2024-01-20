package com.vortex.android.vortexdictionary.repository

import com.vortex.android.vortexdictionary.database.WordDao
import com.vortex.android.vortexdictionary.repository.model.Word
import javax.inject.Inject

class WordRepository @Inject constructor(
    private val databaseDao : WordDao
) : BaseWordRepository {

    override suspend fun getRandomWord() = databaseDao.getRandomWord()

    override suspend fun addWord(word: Word) = databaseDao.addWord(word)

    override suspend fun getAllWords() = databaseDao.getWords()

    override suspend fun searchDatabase(searchQuery: String) = databaseDao.searchDatabase(searchQuery)

    override suspend fun deleteDatabase() = databaseDao.deleteDatabase()

    override fun getSubscriptionPrice(): String {
        return "5$/Месяц"
    }

    //Сортировка

    override fun getItemsSortedByDateAscended() = databaseDao.getItemsSortedByDateAscended()

    override fun getItemsSortedByDateDescended() = databaseDao.getItemsSortedByDateDescended()

    override fun getItemsSortedByLatinAscended() = databaseDao.getItemsSortedByLatinAscended()

    override fun getItemsSortedByLatinDescended() = databaseDao.getItemsSortedByLatinDescended()

    override fun getItemsSortedByCyrillicAscended() = databaseDao.getItemsSortedByCyrillicAscended()

    override fun getItemsSortedByCyrillicDescended() = databaseDao.getItemsSortedByCyrillicDescended()
}