package com.basar.spacextracker.ui.rockets

import com.basar.spacextracker.domain.uimodel.RocketUIItem

data class RocketsUIState(
    var isLoading: Boolean,
    var items: List<RocketUIItem>
)