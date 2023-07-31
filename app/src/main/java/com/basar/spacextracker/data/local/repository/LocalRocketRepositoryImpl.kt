package com.basar.spacextracker.data.local.repository

import com.basar.spacextracker.data.local.database.RocketDao
import com.basar.spacextracker.data.local.model.Rocket
import com.basar.spacextracker.data.local.model.RocketWithImages
import com.basar.spacextracker.domain.repository.LocalRocketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRocketRepositoryImpl @Inject constructor(
    private val rocketDao: RocketDao
) : LocalRocketRepository {
    override fun getRocketList(): Flow<List<Rocket>> = rocketDao.getRocketList()
    override fun getFavouriteRockets(): Flow<List<Rocket>> = rocketDao.getFavouriteRockets()
    override fun getRocketWithImages(): Flow<List<RocketWithImages>> = rocketDao.getRocketWithImages()
    override fun updateRocket(rocket: Rocket) = rocketDao.updateRocket(rocket)
    override suspend fun deleteAllRockets() = rocketDao.deleteAllRockets()
    override suspend fun deleteRocketById(id: Int) = rocketDao.deleteRocketById(id)
}