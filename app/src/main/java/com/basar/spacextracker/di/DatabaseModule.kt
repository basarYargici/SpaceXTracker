package com.basar.spacextracker.di

import android.content.Context
import androidx.room.Room
import com.basar.spacextracker.data.local.database.RocketDao
import com.basar.spacextracker.data.local.database.RocketDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideItemDao(appDatabase: RocketDatabase): RocketDao = appDatabase.rocketDao()

    @Provides
    @Singleton
    fun provideRocketDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext.applicationContext, RocketDatabase::class.java, "rocket_db"
    ).build()
}