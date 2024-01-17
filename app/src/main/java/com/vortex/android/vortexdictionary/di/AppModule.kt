package com.vortex.android.vortexdictionary.di

import android.content.Context
import androidx.room.Room
import com.vortex.android.vortexdictionary.database.WordDao
import com.vortex.android.vortexdictionary.database.WordDatabase
import com.vortex.android.vortexdictionary.repository.BaseWordRepository
import com.vortex.android.vortexdictionary.repository.WordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "DICTIONARY"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ) = Room.databaseBuilder(
                appContext,
                WordDatabase::class.java,
                DATABASE_NAME
            )
            //.createFromAsset()
            .build()

    @Singleton
    @Provides
    fun provideDao(database: WordDatabase) = database.wordDao()

    @Singleton
    @Provides
    fun provideRepository(databaseDao: WordDao) = WordRepository(databaseDao)
}