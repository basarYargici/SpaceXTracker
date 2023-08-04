package com.basar.spacextracker.domain.dashboard

import com.basar.spacextracker.domain.repository.RocketRepository
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllRocketsUseCase @Inject constructor(
    private val rocketRepository: RocketRepository
) {
    suspend operator fun invoke(): Flow<List<RocketUIItem>?> = rocketRepository.getRocketList().map {
        it?.map { rocket ->
            with(rocket) {
                RocketUIItem(
                    id ?: "0",
                    flickrImages,
                    name,
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