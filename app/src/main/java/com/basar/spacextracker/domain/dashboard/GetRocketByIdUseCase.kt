package com.basar.spacextracker.domain.dashboard

import com.basar.spacextracker.domain.repository.RocketRepository
import com.basar.spacextracker.domain.uimodel.RocketDetailUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRocketByIdUseCase @Inject constructor(
    private val rocketRepository: RocketRepository
) {
    suspend operator fun invoke(id: Int): Flow<RocketDetailUI?> =
        rocketRepository.getRocketById(id).map { rocket ->
            RocketDetailUI(
                rocket.flickrImages?.firstOrNull()
            )
        }
}