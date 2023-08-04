package com.basar.spacextracker.ui.dashboard.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basar.spacextracker.domain.favourites.DeleteFavouriteRocketUseCase
import com.basar.spacextracker.domain.favourites.GetFavouriteRocketUseCase
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouriteRocketUseCase: GetFavouriteRocketUseCase,
    private val deleteFavouriteRocketUseCase: DeleteFavouriteRocketUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavouriteUIState(true, emptyList()))
    var uiState = _uiState.asStateFlow()
    private var favList: MutableList<RocketUIItem>? = null

    fun getFavouriteRocketList() = viewModelScope.launch(Dispatchers.IO) {
        getFavouriteRocketUseCase().onStart {
            _uiState.emit(
                _uiState.value.copy(isLoading = true)
            )
        }.onCompletion {
            _uiState.emit(
                _uiState.value.copy(isLoading = false)
            )
        }.catch {
            Timber.d("e $it")
        }.collect { itemList ->
            favList = itemList?.toMutableList()
            _uiState.emit(
                _uiState.value.copy(
                    isLoading = false, items = favList ?: emptyList()
                )
            )
        }
    }

    fun deleteRocket(id: String) = viewModelScope.launch(Dispatchers.IO) {
        Timber.d("coroutine: ${this.coroutineContext}")
        deleteFavouriteRocketUseCase(id)
        _uiState.emit(
            _uiState.value.copy(
                isLoading = false, items = favList ?: emptyList()
            )
        )
    }
}

