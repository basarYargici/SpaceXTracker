package com.basar.spacextracker.domain.favourites

import com.basar.spacextracker.domain.repository.LocalRocketRepository
import javax.inject.Inject

class UpdateFavouriteRocketUseCase @Inject constructor(
    private val rocketRepository: LocalRocketRepository
) {
    operator fun invoke(rocketId: Int, isFavourite: Boolean): Unit =
        rocketRepository.updateRocket(rocketId, isFavourite)
}