package com.basar.spacextracker.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basar.spacextracker.domain.favourites.DeleteFavouriteRocketUseCase
import com.basar.spacextracker.domain.favourites.GetFavouriteRocketUseCase
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
class FavouritesViewModel @Inject constructor(
    private val getFavouriteRocketUseCase: GetFavouriteRocketUseCase,
    private val deleteFavouriteRocketUseCase: DeleteFavouriteRocketUseCase
) : ViewModel() {
    private val _rocketList = MutableStateFlow<List<RocketListUI>?>(null)
    var rocketList = _rocketList

    fun initVM() {
        getFavouriteRocketList()
    }

    private fun getFavouriteRocketList() = viewModelScope.launch(Dispatchers.IO) {
        Timber.d("coroutine: ${this.coroutineContext}")
        getFavouriteRocketUseCase().onStart {
            Timber.d("Onstart")
        }.onCompletion {
            Timber.d("onCompletion")
        }.catch {
            Timber.d("e $it")
        }.collect {
            Timber.d("collect")
            rocketList.emit(it)
//                isSuccessfullyUpdated.emit(it)
            Timber.d("isSuccessfullyUpdated $it")
        }
    }

    fun deleteRocket(id: String) = viewModelScope.launch(Dispatchers.IO) {
        Timber.d("coroutine: ${this.coroutineContext}")
        deleteFavouriteRocketUseCase(id)
    }
}

