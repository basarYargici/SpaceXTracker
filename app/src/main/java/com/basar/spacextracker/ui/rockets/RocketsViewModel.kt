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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.joinAll
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
        val jobs = listOf(launch { getRockets() }, launch { getFavouriteRockets() })
        jobs.joinAll()

        Timber.d("here: ${this.coroutineContext}")
        rocketList.map { item ->
            if (favList.firstOrNull { it.id == item.id } != null) {
                item.isFavourite = true
            }
        }
        _uiState.emit(
            _uiState.value.copy(isLoading = false, items = rocketList)
        )
    }

    private suspend fun getRockets() = rocketUseCase().onStart {
        _uiState.emit(
            _uiState.value.copy(isLoading = true)
        )
    }.collect { itemList ->
        rocketList = itemList ?: emptyList()
    }

    private suspend fun getFavouriteRockets() = getFavouriteRocketUseCase().collect { itemList ->
        favList = itemList ?: emptyList()
    }

    fun addRocket(rocket: Rocket) = viewModelScope.launch(Dispatchers.IO) {
        Timber.d("coroutine: ${this.coroutineContext}")
        addFavouriteRocketUseCase(rocket).catch {
            Timber.d("e $it")
        }.collect {}
    }

    fun deleteRocket(id: String) = viewModelScope.launch(Dispatchers.IO) {
        deleteFavouriteRocketUseCase(id)
    }
}