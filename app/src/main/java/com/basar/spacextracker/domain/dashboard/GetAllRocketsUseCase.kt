package com.basar.spacextracker.domain.dashboard

import com.basar.spacextracker.data.remote.response.Rocket
import com.basar.spacextracker.domain.favourites.GetFavouriteRocketUseCase
import com.basar.spacextracker.domain.repository.RocketRepository
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import com.basar.spacextracker.domain.uimodel.toRocketUIItem
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
            it?.map(Rocket::toRocketUIItem)
        }.zip(getFavouriteRocketUseCase()) { itemList, favs ->
            rocketList = itemList ?: emptyList()
            favList = favs ?: emptyList()
            setFavouritesInRocketList()
        }
    }

    private fun setFavouritesInRocketList(): List<RocketUIItem> = rocketList.map { item ->
        if (favList.firstOrNull { it.id == item.id } != null) {
            item.isFavourite = true
        }
        item
    }
}