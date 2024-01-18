package com.vortex.android.vortexdictionary.repository

import com.vortex.android.vortexdictionary.database.WordDao
import javax.inject.Inject

class WordRepository @Inject constructor(
    private val databaseDao : WordDao
) : BaseWordRepository {

    override suspend fun getRandomWord() = databaseDao.getRandomWord()

}