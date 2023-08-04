package com.basar.spacextracker.data.local.database

import androidx.room.*
import com.basar.spacextracker.data.local.model.Rocket
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao {

    @Query("SELECT * FROM rocket ORDER BY rocket_id ASC")
    fun getRocketList(): Flow<List<Rocket>>

    @Query("SELECT * FROM rocket WHERE is_favorite = 1")
    fun getFavouriteRockets(): List<Rocket>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRocket(rocket: Rocket)

    @Query("DELETE FROM rocket WHERE rocket_id = :id")
    suspend fun deleteRocketById(id: String)
}