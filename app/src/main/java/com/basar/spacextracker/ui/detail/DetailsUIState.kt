package com.basar.spacextracker.ui.detail

import com.basar.spacextracker.domain.uimodel.RocketDetailUIItem

data class DetailsUIState(
    var isLoading: Boolean, var item: RocketDetailUIItem? = null
)