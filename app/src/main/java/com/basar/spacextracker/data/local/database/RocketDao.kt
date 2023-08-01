package com.basar.spacextracker.data.local.database

import androidx.room.*
import com.basar.spacextracker.data.local.model.ImageUrl
import com.basar.spacextracker.data.local.model.Rocket
import com.basar.spacextracker.data.local.model.RocketWithImages
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao {

    @Query("SELECT * FROM rocket WHERE rocket_id IN (SELECT DISTINCT(rocket_id) FROM image_url)")
    fun getRocketList(): Flow<List<RocketWithImages>>

    @Query("SELECT * FROM rocket WHERE is_favorite = 1 ")
    fun getFavouriteRockets(): Flow<List<RocketWithImages>>

    @Transaction
    @Query("SELECT * FROM rocket")
    fun getRocketWithImages(): Flow<List<RocketWithImages>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addRocket(rocket: Rocket): Unit
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addImageUrls(imageUrl: List<ImageUrl>): Unit

    @Query("DELETE FROM rocket")
    suspend fun deleteAllRockets()

    @Query("DELETE FROM rocket WHERE rocket_id = :id")
    suspend fun deleteRocketById(id: Int)
}