package com.basar.spacextracker.domain.repository

import com.basar.spacextracker.data.local.model.Rocket
import com.basar.spacextracker.data.local.model.RocketWithImages
import kotlinx.coroutines.flow.Flow

interface LocalRocketRepository {
    fun getRocketList(): Flow<List<Rocket>>
    fun getFavouriteRockets(): Flow<List<Rocket>>
    fun getRocketWithImages(): Flow<List<RocketWithImages>>
    fun updateRocket(rocket: Rocket)
    suspend fun deleteAllRockets()
    suspend fun deleteRocketById(id: Int)
}
