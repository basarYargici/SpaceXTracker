package com.basar.spacextracker.domain.favourites

import com.basar.spacextracker.data.local.model.Rocket
import com.basar.spacextracker.domain.repository.LocalRocketRepository
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import com.basar.spacextracker.domain.uimodel.toRocketUIItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavouriteRocketUseCase @Inject constructor(
    private val rocketRepository: LocalRocketRepository
) {

    operator fun invoke(): Flow<List<RocketUIItem>?> = rocketRepository.getFavouriteRockets().map {
        it.map(Rocket::toRocketUIItem)
    }
}