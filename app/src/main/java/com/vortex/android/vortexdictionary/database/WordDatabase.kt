package com.vortex.android.vortexdictionary.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.vortex.android.vortexdictionary.repository.model.Word
import java.util.Date

@Database(entities = [ Word::class ], version = 1, exportSchema = true)
@TypeConverters(WordTypeConverters::class)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}

class WordTypeConverters { //Конвертеры для кастомных классов
    @TypeConverter
    fun fromDate(date: Date) : Long {
        return date.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long) : Date {
        return Date(millisSinceEpoch)
    }
}