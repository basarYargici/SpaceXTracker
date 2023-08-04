package com.basar.spacextracker.domain.detail

import com.basar.spacextracker.domain.repository.RocketRepository
import com.basar.spacextracker.domain.uimodel.RocketDetailUIItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRocketByIdUseCase @Inject constructor(
    private val rocketRepository: RocketRepository
) {
    suspend operator fun invoke(id: String): Flow<RocketDetailUIItem?> =
        rocketRepository.getRocketById(id).map { rocket ->
            with(rocket) {
                RocketDetailUIItem(
                    id,
                    rocket.flickrImages,
                    name,
                    active,
                    firstFlight,
                    country,
                    company,
                    wikipedia,
                    description,
                    height?.meters.toString(),
                    mass?.kg.toString(),
                    engines?.number.toString(),
                    isFavourite = false
                )
            }
        }
}