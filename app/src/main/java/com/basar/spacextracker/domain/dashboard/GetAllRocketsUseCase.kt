package com.basar.spacextracker.domain.dashboard

import com.basar.spacextracker.domain.favourites.GetFavouriteRocketUseCase
import com.basar.spacextracker.domain.repository.RocketRepository
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetAllRocketsUseCase @Inject constructor(
    private val rocketRepository: RocketRepository,
    private val getFavouriteRocketUseCase: GetFavouriteRocketUseCase,
) {

    private var favList: List<RocketUIItem> = emptyList()
    private var rocketList: List<RocketUIItem> = emptyList()

    suspend operator fun invoke(): Flow<List<RocketUIItem>?> {
        return rocketRepository.getRocketList().map {
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
        }.zip(getFavouriteRocketUseCase()) { itemList, favs ->
            rocketList = itemList ?: emptyList()
            favList = favs ?: emptyList()
            rocketList.map { item ->
                if (favList.firstOrNull { it.id == item.id } != null) {
                    item.isFavourite = true
                }
            }
            rocketList
        }
    }
}