package com.basar.spacextracker.ui.rockets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basar.spacextracker.data.local.model.Rocket
import com.basar.spacextracker.domain.dashboard.GetAllRocketsUseCase
import com.basar.spacextracker.domain.favourites.AddFavouriteRocketUseCase
import com.basar.spacextracker.domain.favourites.DeleteFavouriteRocketUseCase
import com.basar.spacextracker.domain.uimodel.RocketListUI
import dagger.hilt.android.lifecycle.HiltViewModel
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
) : ViewModel() {
    private val _rocketList = MutableStateFlow<List<RocketListUI>?>(null)
    var rocketList = _rocketList

    private val _showLoading = MutableStateFlow(true)
    val showLoading: StateFlow<Boolean> = _showLoading

    fun initVM() {
        getRocketList()
    }

    private fun getRocketList() = viewModelScope.launch(Dispatchers.IO) {
        Timber.d("coroutine: ${this.coroutineContext}")
        rocketUseCase.invoke().onStart {
            _showLoading.emit(true)
        }.onCompletion {
            _showLoading.emit(false)
        }.catch {
            Timber.d("e $it")
        }.collect {
            Timber.d("collect")
            _rocketList.emit(it)
            Timber.d("rocket ${rocketList.value}")
        }
    }

    fun addRocket(rocket: Rocket) = viewModelScope.launch(Dispatchers.IO) {
        Timber.d("coroutine: ${this.coroutineContext}")
        addFavouriteRocketUseCase(rocket).onStart {
            Timber.d("Onstart")
        }.onCompletion {
            Timber.d("onCompletion")
        }.catch {
            Timber.d("e $it")
        }.collect {
            Timber.d("collect")
            Timber.d("isSuccessfullyUpdated $it")
        }
    }

    fun deleteRocket(id: String) = viewModelScope.launch(Dispatchers.IO) {
        Timber.d("coroutine: ${this.coroutineContext}")
        deleteFavouriteRocketUseCase(id)
    }
}

