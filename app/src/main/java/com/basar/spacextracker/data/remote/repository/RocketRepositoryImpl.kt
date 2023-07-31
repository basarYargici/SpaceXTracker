package com.basar.spacextracker.data.remote.repository

import com.basar.spacextracker.data.remote.response.AllRocketsResponse
import com.basar.spacextracker.data.remote.service.RocketsService
import com.basar.spacextracker.domain.repository.RocketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RocketRepositoryImpl @Inject constructor(
    private val rocketsService: RocketsService
) : RocketRepository {
    override suspend fun getRocketList(): Flow<AllRocketsResponse> = rocketsService.getRocketList()
}