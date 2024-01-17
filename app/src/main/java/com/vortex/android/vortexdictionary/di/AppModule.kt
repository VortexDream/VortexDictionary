package com.vortex.android.vortexdictionary.di

import android.content.Context
import androidx.room.Room
import com.vortex.android.vortexdictionary.database.WordDatabase
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
}