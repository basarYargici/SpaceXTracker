package com.basar.spacextracker.ui.rockets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basar.spacextracker.data.local.model.Rocket
import com.basar.spacextracker.domain.dashboard.GetAllRocketsUseCase
import com.basar.spacextracker.domain.favourites.AddFavouriteRocketUseCase
import com.basar.spacextracker.domain.favourites.DeleteFavouriteRocketUseCase
import com.basar.spacextracker.domain.favourites.GetFavouriteRocketUseCase
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RocketsViewModel @Inject constructor(
    private val rocketUseCase: GetAllRocketsUseCase,
    private val addFavouriteRocketUseCase: AddFavouriteRocketUseCase,
    private val deleteFavouriteRocketUseCase: DeleteFavouriteRocketUseCase,
    private val getFavouriteRocketUseCase: GetFavouriteRocketUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RocketsUIState(true, emptyList()))
    var uiState = _uiState.asStateFlow()

    private var favList: List<RocketUIItem> = emptyList()
    private var rocketList: List<RocketUIItem> = emptyList()
    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        val error = e.message
        Timber.v("exceptionHandler : $error")
    }

    fun getRocketList() = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
        getRemoteRockets().zip(getFavouriteRockets()) { itemList, favs ->
            rocketList = itemList ?: emptyList()
            favList = favs ?: emptyList()
        }.collect()

        rocketList.map { item ->
            if (favList.firstOrNull { it.id == item.id } != null) {
                item.isFavourite = true
            }
        }
        _uiState.emit(
            _uiState.value.copy(isLoading = false, items = rocketList)
        )
    }

    private suspend fun getRemoteRockets(): Flow<List<RocketUIItem>?> = rocketUseCase().onStart {
        _uiState.emit(
            _uiState.value.copy(isLoading = true)
        )
    }

    private fun getFavouriteRockets(): Flow<List<RocketUIItem>?> = getFavouriteRocketUseCase()

    fun addRocket(rocket: Rocket) = viewModelScope.launch(Dispatchers.IO) {
        addFavouriteRocketUseCase(rocket).catch {
            Timber.d("e $it")
        }.collect {}
    }

    fun deleteRocket(id: String) = viewModelScope.launch(Dispatchers.IO) {
        deleteFavouriteRocketUseCase(id)
    }
}