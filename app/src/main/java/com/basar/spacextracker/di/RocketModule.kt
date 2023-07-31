package com.basar.spacextracker.di

import com.basar.spacextracker.data.remote.repository.RocketRepositoryImpl
import com.basar.spacextracker.data.remote.service.RocketsService
import com.basar.spacextracker.domain.repository.RocketRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RocketModule {
    @Provides
    @Singleton
    fun provideRocketService(retrofit: Retrofit): RocketsService = retrofit.create(RocketsService::class.java)

    @Provides
    @Singleton
    fun provideRocketListRepository(
        service: RocketsService
    ): RocketRepository = RocketRepositoryImpl(service)
}