package com.basar.spacextracker.domain.repository

import com.basar.spacextracker.data.remote.response.AllRocketsResponse
import com.basar.spacextracker.data.remote.response.Rocket
import kotlinx.coroutines.flow.Flow

interface RocketRepository {
    suspend fun getRocketList(): Flow<AllRocketsResponse>
    suspend fun getRocketById(id: Int): Flow<Rocket>
}
