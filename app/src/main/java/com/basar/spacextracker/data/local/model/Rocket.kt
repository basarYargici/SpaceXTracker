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
    @ColumnInfo(name = "rocketName") val name: String? = null,
    @ColumnInfo(name = "country") val country: String? = null,
    @ColumnInfo(name = "company") val company: String? = null,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean? = null,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "images") val images: String? = null,
    @ColumnInfo(name = "wikipedia") val wikipedia: String? = null,
    @ColumnInfo(name = "height") val height: String? = null,
    @ColumnInfo(name = "weight") val weight: String? = null,
    @ColumnInfo(name = "engineCount") val engineCount: String? = null,
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