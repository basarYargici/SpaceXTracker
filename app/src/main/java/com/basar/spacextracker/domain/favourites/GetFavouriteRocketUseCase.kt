package com.basar.spacextracker.domain.favourites

import com.basar.spacextracker.domain.repository.LocalRocketRepository
import com.basar.spacextracker.domain.uimodel.RocketDetailUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavouriteRocketUseCase @Inject constructor(
    private val rocketRepository: LocalRocketRepository
) {
    operator fun invoke(): Flow<List<RocketDetailUI>?> = rocketRepository.getFavouriteRockets().map {
        it.map { rocketWithImages ->
            val images = rocketWithImages.imageUrls
            val rocket = rocketWithImages.rocket
            RocketDetailUI(images.map { image -> image.url ?: "" })
        }
    }
}