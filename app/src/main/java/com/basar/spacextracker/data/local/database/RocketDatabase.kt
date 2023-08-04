package com.basar.spacextracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.basar.spacextracker.data.local.model.Converters
import com.basar.spacextracker.data.local.model.Rocket

@Database(
    entities = [Rocket::class], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RocketDatabase : RoomDatabase() {

    abstract fun rocketDao(): RocketDao
}