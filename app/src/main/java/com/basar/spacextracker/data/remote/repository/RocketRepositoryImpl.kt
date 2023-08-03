package com.basar.spacextracker.data.remote.repository

import com.basar.spacextracker.data.remote.response.Rocket
import com.basar.spacextracker.data.remote.service.RocketsService
import com.basar.spacextracker.domain.repository.RocketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RocketRepositoryImpl @Inject constructor(
    private val rocketsService: RocketsService
) : RocketRepository {
    override suspend fun getRocketList(): Flow<List<Rocket>?> = flowOf(rocketsService.getRocketList())
    override suspend fun getRocketById(id: String): Flow<Rocket> = flowOf(rocketsService.getRocketById(id))
}