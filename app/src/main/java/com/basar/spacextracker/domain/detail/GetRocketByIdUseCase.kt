package com.basar.spacextracker.domain.detail

import com.basar.spacextracker.data.remote.response.Rocket
import com.basar.spacextracker.domain.repository.RocketRepository
import com.basar.spacextracker.domain.uimodel.RocketDetailUIItem
import com.basar.spacextracker.domain.uimodel.toRocketDetailUIItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRocketByIdUseCase @Inject constructor(
    private val rocketRepository: RocketRepository
) {

    suspend operator fun invoke(id: String): Flow<RocketDetailUIItem?> =
        rocketRepository.getRocketById(id).map(Rocket::toRocketDetailUIItem)
}