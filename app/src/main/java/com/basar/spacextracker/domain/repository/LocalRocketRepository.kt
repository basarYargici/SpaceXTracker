package com.basar.spacextracker.domain.repository

import com.basar.spacextracker.data.local.model.Rocket
import kotlinx.coroutines.flow.Flow

interface LocalRocketRepository {

    fun getRocketList(): Flow<List<Rocket>>
    fun getFavouriteRockets(): Flow<List<Rocket>>
    fun addRocket(rocket: Rocket)
    suspend fun deleteRocketById(id: String)
}
