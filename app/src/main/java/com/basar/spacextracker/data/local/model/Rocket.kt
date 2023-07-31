package com.basar.spacextracker.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rocket")
data class Rocket(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "rocket_id") val id: Int,
    @ColumnInfo(name = "rocketName") val rocketName: String,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "company") val company: String,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
    @ColumnInfo(name = "description") val description: String
)

