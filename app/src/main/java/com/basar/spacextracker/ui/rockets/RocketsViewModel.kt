package com.basar.spacextracker.ui.rockets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basar.spacextracker.data.local.model.RocketWithImages
import com.basar.spacextracker.domain.dashboard.GetAllRocketsUseCase
import com.basar.spacextracker.domain.favourites.AddFavouriteRocketUseCase
import com.basar.spacextracker.domain.uimodel.RocketListUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RocketsViewModel @Inject constructor(
    private val rocketUseCase: GetAllRocketsUseCase,
    private val addFavouriteRocketUseCase: AddFavouriteRocketUseCase
) : ViewModel() {
    private val _rocketList = MutableStateFlow<List<RocketListUI>?>(null)
    var rocketList = _rocketList

    private val _isSuccessfullyUpdated = MutableStateFlow<Boolean?>(null)
    var isSuccessfullyUpdated = _isSuccessfullyUpdated

    fun initVM() {
        getRocketList()
    }

    private fun getRocketList() = viewModelScope.launch(Dispatchers.IO) {
        Timber.d("coroutine: ${this.coroutineContext}")
        rocketUseCase.invoke().onStart {
            Timber.d("Onstart")
        }.onCompletion {
            Timber.d("onCompletion")
        }.catch {
            Timber.d("e $it")
        }.collect {
            Timber.d("collect")
            _rocketList.emit(it)
            Timber.d("rocket ${rocketList.value}")
        }
    }

    fun addRocket(rocket: RocketWithImages) =
        viewModelScope.launch(Dispatchers.IO) {
            Timber.d("coroutine: ${this.coroutineContext}")
            addFavouriteRocketUseCase(rocket).onStart {
                Timber.d("Onstart")
            }.onCompletion {
                Timber.d("onCompletion")
            }.catch {
                Timber.d("e $it")
            }.collect {
                Timber.d("collect")
                isSuccessfullyUpdated.emit(it)
                Timber.d("isSuccessfullyUpdated $it")
            }
        }
}

