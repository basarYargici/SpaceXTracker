package com.basar.spacextracker.domain.repository

import com.basar.spacextracker.data.remote.response.AllRocketsResponse
import kotlinx.coroutines.flow.Flow

interface RocketsRepository {
    suspend fun getRocketList(): Flow<AllRocketsResponse>
}
