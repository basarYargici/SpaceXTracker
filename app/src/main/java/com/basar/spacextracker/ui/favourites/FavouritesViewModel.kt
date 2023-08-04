package com.basar.spacextracker.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basar.spacextracker.domain.favourites.DeleteFavouriteRocketUseCase
import com.basar.spacextracker.domain.favourites.GetFavouriteRocketUseCase
import com.basar.spacextracker.domain.uimodel.RocketListUI
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
    private val _rocketList = MutableStateFlow<List<RocketListUI>?>(null)
    var rocketList = _rocketList

    private val _shouldShowLoading = MutableStateFlow(true)
    val shouldShowLoading: StateFlow<Boolean> = _shouldShowLoading

    private val _showShowNoItemFound = MutableStateFlow(true)
    val showShowNoItemFound: StateFlow<Boolean> = _showShowNoItemFound

    fun initVM() {
        getFavouriteRocketList()
    }

    private fun getFavouriteRocketList() = viewModelScope.launch(Dispatchers.IO) {
        getFavouriteRocketUseCase().onStart {
            _shouldShowLoading.emit(true)
        }.onCompletion {
            _shouldShowLoading.emit(false)
        }.catch {
            Timber.d("e $it")
        }.collect {
            rocketList.emit(it)
            _showShowNoItemFound.emit(it.isNullOrEmpty())
        }
    }

    fun deleteRocket(id: String) = viewModelScope.launch(Dispatchers.IO) {
        Timber.d("coroutine: ${this.coroutineContext}")
        deleteFavouriteRocketUseCase(id)
    }
}

