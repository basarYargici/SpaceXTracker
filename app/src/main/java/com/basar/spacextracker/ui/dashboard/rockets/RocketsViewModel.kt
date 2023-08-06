package com.basar.spacextracker.ui.dashboard.rockets

import androidx.lifecycle.ViewModel
import com.basar.spacextracker.data.local.model.Rocket
import com.basar.spacextracker.domain.dashboard.GetAllRocketsUseCase
import com.basar.spacextracker.domain.favourites.AddFavouriteRocketUseCase
import com.basar.spacextracker.domain.favourites.DeleteFavouriteRocketUseCase
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import com.basar.spacextracker.ext.launchViewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class RocketsViewModel @Inject constructor(
    private val rocketUseCase: GetAllRocketsUseCase,
    private val addFavouriteRocketUseCase: AddFavouriteRocketUseCase,
    private val deleteFavouriteRocketUseCase: DeleteFavouriteRocketUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RocketsUIState(true, emptyList()))
    var uiState = _uiState.asStateFlow()

    private var rocketList: List<RocketUIItem> = emptyList()

    fun getRocketList() = launchViewModelScope {
        rocketUseCase().onStart {
            _uiState.emit(
                _uiState.value.copy(isLoading = true)
            )
        }.collect {
            rocketList = it ?: emptyList()
        }

        _uiState.emit(
            _uiState.value.copy(isLoading = false, items = rocketList)
        )
    }

    fun addRocket(rocket: Rocket) = launchViewModelScope {
        addFavouriteRocketUseCase(rocket).collect()
    }

    fun deleteRocket(id: String) = launchViewModelScope {
        deleteFavouriteRocketUseCase(id)
    }

    fun unfavouredRocket(id: String) {
        _uiState.value.items.firstOrNull { it.id == id }?.isFavourite = false
    }
}