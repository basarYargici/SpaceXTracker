package com.basar.spacextracker.domain.favourites

import com.basar.spacextracker.data.local.model.Converters
import com.basar.spacextracker.domain.repository.LocalRocketRepository
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavouriteRocketUseCase @Inject constructor(
    private val rocketRepository: LocalRocketRepository
) {

    operator fun invoke(): Flow<List<RocketUIItem>?> = rocketRepository.getFavouriteRockets().map {
        it.map { rocket ->
            with(rocket) {
                RocketUIItem(
                    id,
                    Converters.fromString(images),
                    name,
                    country,
                    company,
                    wikipedia,
                    description,
                    height,
                    weight,
                    engineCount
                )

            }
        }
    }
}