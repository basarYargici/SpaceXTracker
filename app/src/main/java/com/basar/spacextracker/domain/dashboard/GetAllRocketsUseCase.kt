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
        it.rockets?.map { rocket ->
            RocketListUI(
                rocket.flickrImages?.firstOrNull()
            )
        }
    }
}