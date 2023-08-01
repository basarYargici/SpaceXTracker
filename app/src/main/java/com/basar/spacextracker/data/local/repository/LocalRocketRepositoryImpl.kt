package com.basar.spacextracker.data.local.repository

import com.basar.spacextracker.data.local.database.RocketDao
import com.basar.spacextracker.data.local.model.ImageUrl
import com.basar.spacextracker.data.local.model.Rocket
import com.basar.spacextracker.data.local.model.RocketWithImages
import com.basar.spacextracker.domain.repository.LocalRocketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRocketRepositoryImpl @Inject constructor(
    private val rocketDao: RocketDao
) : LocalRocketRepository {
    override fun getRocketList(): Flow<List<RocketWithImages>> = rocketDao.getRocketList()
    override fun getFavouriteRockets(): Flow<List<RocketWithImages>> = rocketDao.getFavouriteRockets()
    override fun getRocketWithImages(): Flow<List<RocketWithImages>> = rocketDao.getRocketWithImages()
    override fun addRocket(rocket: Rocket): Unit = rocketDao.addRocket(rocket)
    override fun addImageUrls(imageUrl: List<ImageUrl>): Unit = rocketDao.addImageUrls(imageUrl)
    override suspend fun deleteAllRockets() = rocketDao.deleteAllRockets()
    override suspend fun deleteRocketById(id: Int) = rocketDao.deleteRocketById(id)
}