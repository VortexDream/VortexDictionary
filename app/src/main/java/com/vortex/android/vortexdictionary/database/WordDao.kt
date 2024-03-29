package com.vortex.android.vortexdictionary.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vortex.android.vortexdictionary.repository.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM word")
    fun getWords(): Flow<List<Word>>

    @Query("SELECT * FROM word WHERE englishText= :word")
    suspend fun getWord(word: String): Word

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWord(word: Word)

    @Query("SELECT * FROM word ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomWord(): Word?

    @Query("SELECT * FROM word WHERE englishText LIKE :searchQuery OR russianText LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Word>>

    @Query("DELETE FROM word")
    fun deleteDatabase()

    //Sorting

    @Query("SELECT * FROM word ORDER BY date ASC")
    fun getItemsSortedByDateAscended(): Flow<List<Word>>

    @Query("SELECT * FROM word ORDER BY date DESC")
    fun getItemsSortedByDateDescended(): Flow<List<Word>>

    @Query("SELECT * FROM word ORDER BY englishText ASC")
    fun getItemsSortedByLatinAscended(): Flow<List<Word>>

    @Query("SELECT * FROM word ORDER BY englishText DESC")
    fun getItemsSortedByLatinDescended(): Flow<List<Word>>

    @Query("SELECT * FROM word ORDER BY russianText ASC")
    fun getItemsSortedByCyrillicAscended(): Flow<List<Word>>

    @Query("SELECT * FROM word ORDER BY russianText DESC")
    fun getItemsSortedByCyrillicDescended(): Flow<List<Word>>
}