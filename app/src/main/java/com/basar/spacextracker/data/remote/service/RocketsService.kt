package com.basar.spacextracker.data.remote.service

import com.basar.spacextracker.data.remote.response.AllRocketsResponse
import com.basar.spacextracker.data.remote.response.Rocket
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface RocketsService {

    @GET("rockets")
    suspend fun getRocketList(): Flow<AllRocketsResponse>

    @GET("rocket/{rocket_id}")
    suspend fun getRocketById(@Path("rocket_id") rocketId: Int): Flow<Rocket>
}
