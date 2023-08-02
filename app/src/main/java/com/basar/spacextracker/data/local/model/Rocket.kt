package com.basar.spacextracker.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Entity(tableName = "rocket")
data class Rocket(
    @PrimaryKey @ColumnInfo(name = "rocket_id") val id: String,
    @ColumnInfo(name = "rocketName") val rocketName: String,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "company") val company: String,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "images") val images: String
)

object Converters {
    @TypeConverter
    fun fromString(value: String?): List<String> {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}