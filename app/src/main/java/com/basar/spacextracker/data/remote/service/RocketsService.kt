package com.basar.spacextracker.data.remote.service

import com.basar.spacextracker.data.remote.response.Rocket
import retrofit2.http.GET
import retrofit2.http.Path

interface RocketsService {
    @GET("rockets")
    suspend fun getRocketList(): List<Rocket>?

    @GET("rocket/{rocket_id}")
    fun getRocketById(@Path("rocket_id") rocketId: Int): Rocket
}
