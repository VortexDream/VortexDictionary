package com.vortex.android.vortexdictionary.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Word(
    @PrimaryKey val englishText: String,
    val russianText: String,
    val date: Date,
) {
}