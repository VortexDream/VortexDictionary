package com.vortex.android.vortexdictionary.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.vortex.android.vortexdictionary.repository.AppPreferences
import com.vortex.android.vortexdictionary.database.WordDao
import com.vortex.android.vortexdictionary.database.WordDatabase
import com.vortex.android.vortexdictionary.repository.BaseAppPreferences
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
    fun provideWordRepository(databaseDao: WordDao): BaseWordRepository {
        return WordRepository(databaseDao)
    }

    @Singleton
    @Provides
    fun provideDataStore(
        @ApplicationContext appContext: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            appContext.preferencesDataStoreFile("settings")
        }
    }

    @Singleton
    @Provides
    fun provideAppPreferences(
        dataStore: DataStore<Preferences>
    ): BaseAppPreferences {
        return AppPreferences(dataStore)
    }
}