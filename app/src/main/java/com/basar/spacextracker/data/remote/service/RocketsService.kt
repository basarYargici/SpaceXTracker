package com.basar.spacextracker.data.remote.service

import com.basar.spacextracker.data.remote.response.AllRocketsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface RocketsService {

    @GET("rockets")
    suspend fun getRocketList(): Flow<AllRocketsResponse>
}
