package com.basar.spacextracker.ui.dashboard.favourites

import androidx.lifecycle.ViewModel
import com.basar.spacextracker.domain.favourites.DeleteFavouriteRocketUseCase
import com.basar.spacextracker.domain.favourites.GetFavouriteRocketUseCase
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import com.basar.spacextracker.ext.launchViewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouriteRocketUseCase: GetFavouriteRocketUseCase,
    private val deleteFavouriteRocketUseCase: DeleteFavouriteRocketUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavouriteUIState(true, emptyList()))
    var uiState = _uiState.asStateFlow()
    private var favList: MutableList<RocketUIItem>? = null

    fun getFavouriteRocketList() = launchViewModelScope {
        getFavouriteRocketUseCase().onStart {
            _uiState.emit(
                _uiState.value.copy(isLoading = true)
            )
        }.onCompletion {
            _uiState.emit(
                _uiState.value.copy(isLoading = false)
            )
        }.collect { itemList ->
            favList = itemList?.toMutableList()
            _uiState.emit(
                _uiState.value.copy(
                    isLoading = false, items = favList ?: emptyList()
                )
            )
        }
    }

    fun deleteRocket(id: String) = launchViewModelScope {
        deleteFavouriteRocketUseCase(id)
        _uiState.emit(
            _uiState.value.copy(
                isLoading = false, items = favList ?: emptyList()
            )
        )
    }
}

