package com.basar.spacextracker.domain.favourites

import com.basar.spacextracker.data.local.model.Converters
import com.basar.spacextracker.domain.repository.LocalRocketRepository
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavouriteRocketUseCase @Inject constructor(
    private val rocketRepository: LocalRocketRepository
) {

    operator fun invoke(): Flow<List<RocketUIItem>?> = flow {
        val list: List<RocketUIItem> = rocketRepository.getFavouriteRockets().map {
            with(it) {
                RocketUIItem(
                    id,
                    imageUrl = Converters.fromString(images),
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
        emit(list)
    }
}