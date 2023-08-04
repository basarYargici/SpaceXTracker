package com.basar.spacextracker.domain.favourites

import com.basar.spacextracker.domain.repository.LocalRocketRepository
import javax.inject.Inject

class DeleteFavouriteRocketUseCase @Inject constructor(
    private val rocketRepository: LocalRocketRepository
) {

    suspend operator fun invoke(id: String): Unit = rocketRepository.deleteRocketById(id)
}