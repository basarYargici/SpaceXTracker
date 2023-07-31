package com.basar.spacextracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.basar.spacextracker.data.local.model.Rocket

@Database(entities = [Rocket::class], version = 1, exportSchema = false)
abstract class RocketDatabase : RoomDatabase() {
    abstract fun rocketDao(): RocketDao
}