package com.basar.spacextracker.domain.favourites

import com.basar.spacextracker.data.local.model.RocketWithImages
import com.basar.spacextracker.domain.repository.LocalRocketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class AddFavouriteRocketUseCase @Inject constructor(
    private val rocketRepository: LocalRocketRepository
) {
    operator fun invoke(rocket: RocketWithImages): Flow<Boolean> {
        rocketRepository.addRocket(rocket.rocket)
        rocketRepository.addImageUrls(rocket.imageUrls)
        return flowOf(true)
    }
}