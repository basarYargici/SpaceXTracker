package com.basar.spacextracker.domain.repository

import com.basar.spacextracker.data.local.model.RocketWithImages
import kotlinx.coroutines.flow.Flow

interface LocalRocketRepository {
    fun getRocketList(): Flow<List<RocketWithImages>>
    fun getFavouriteRockets(): Flow<List<RocketWithImages>>
    fun getRocketWithImages(): Flow<List<RocketWithImages>>
    fun updateRocket(rocketId: Int, isFavourite: Boolean)
    suspend fun deleteAllRockets()
    suspend fun deleteRocketById(id: Int)
}
