package com.basar.spacextracker.domain.repository

import com.basar.spacextracker.data.remote.response.Rocket
import kotlinx.coroutines.flow.Flow

interface RocketRepository {
    suspend fun getRocketList(): Flow<List<Rocket>?>
    suspend fun getRocketById(id: String): Flow<Rocket>
}
