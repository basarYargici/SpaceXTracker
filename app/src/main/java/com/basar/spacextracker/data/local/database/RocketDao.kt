package com.basar.spacextracker.data.local.database

import androidx.room.*
import com.basar.spacextracker.data.local.model.Rocket
import com.basar.spacextracker.data.local.model.RocketWithImages
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao {

    @Query("SELECT * FROM Rocket ORDER BY id ASC")
    fun getRocketList(): Flow<List<Rocket>>

    @Query("SELECT * FROM Rocket WHERE is_favorite = true ORDER BY id ASC")
    fun getFavouriteRockets(): Flow<List<Rocket>>

    @Transaction
    @Query("SELECT * FROM Rocket")
    fun getRocketWithImages(): Flow<List<RocketWithImages>>

    @Update
    fun updateRocket(rocket: Rocket)

    @Query("DELETE FROM Rocket")
    suspend fun deleteAllRockets()

    @Query("DELETE FROM Rocket WHERE id = :id")
    suspend fun deleteRocketById(id: Int)
}