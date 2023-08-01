package com.basar.spacextracker.domain.dashboard

import com.basar.spacextracker.domain.repository.RocketRepository
import com.basar.spacextracker.domain.uimodel.RocketListUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllRocketsUseCase @Inject constructor(
    private val rocketRepository: RocketRepository
) {
    suspend operator fun invoke(): Flow<List<RocketListUI>?> = rocketRepository.getRocketList().map {
        it?.map { rocket ->
            with(rocket) {
                RocketListUI(
                    flickrImages,
                    name,
                    active,
                    firstFlight,
                    country,
                    company,
                    wikipedia,
                    description,
                    height?.meters.toString(),
                    mass?.kg.toString(),
                    engines?.number.toString()
                )
            }
        }
    }
}