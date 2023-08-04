package com.basar.spacextracker.ui.dashboard.rockets

import com.basar.spacextracker.domain.uimodel.RocketUIItem

data class RocketsUIState(
    var isLoading: Boolean,
    var items: List<RocketUIItem>,
//    var snackBar: SnackBar? = null,
)

//data class SnackBar(
//    var shouldShow: Boolean,
//    var content: String
//)