package com.basar.spacextracker.domain.favourites

import com.basar.spacextracker.data.local.model.Rocket
import com.basar.spacextracker.domain.repository.LocalRocketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class AddFavouriteRocketUseCase @Inject constructor(
    private val rocketRepository: LocalRocketRepository
) {
    operator fun invoke(rocket: Rocket): Flow<Boolean> {
        rocketRepository.addRocket(rocket)
        return flowOf(true)
    }
}