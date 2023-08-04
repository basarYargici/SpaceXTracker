package com.basar.spacextracker.ui.favourites

import com.basar.spacextracker.domain.uimodel.RocketUIItem

data class FavouriteUIState(
    var isLoading: Boolean,
    var items: List<RocketUIItem>
)