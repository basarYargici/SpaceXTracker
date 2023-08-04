package com.basar.spacextracker.data.local.repository

import com.basar.spacextracker.data.local.database.RocketDao
import com.basar.spacextracker.data.local.model.Rocket
import com.basar.spacextracker.domain.repository.LocalRocketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRocketRepositoryImpl @Inject constructor(
    private val rocketDao: RocketDao
) : LocalRocketRepository {

    override fun getRocketList(): Flow<List<Rocket>> = rocketDao.getRocketList()
    override fun getFavouriteRockets(): List<Rocket> = rocketDao.getFavouriteRockets()
    override fun addRocket(rocket: Rocket): Unit = rocketDao.addRocket(rocket)
    override suspend fun deleteRocketById(id: String) = rocketDao.deleteRocketById(id)
}