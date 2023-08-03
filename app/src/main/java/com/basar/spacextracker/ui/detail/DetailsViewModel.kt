package com.basar.spacextracker.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basar.spacextracker.domain.detail.GetRocketByIdUseCase
import com.basar.spacextracker.domain.uimodel.RocketDetailUI
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
class DetailsViewModel @Inject constructor(
    private val rocketDetailUseCase: GetRocketByIdUseCase,
) : ViewModel() {
    private val _rocket = MutableStateFlow<RocketDetailUI?>(null)
    var rocket = _rocket

    fun initVM(id: String) {
        getRocketList(id)
    }

    private fun getRocketList(id: String) = viewModelScope.launch(Dispatchers.IO) {
        rocketDetailUseCase(id).onStart {
            Timber.d("Onstart")
        }.onCompletion {
            Timber.d("onCompletion")
        }.catch {
            Timber.d("e $it")
        }.collect {
            _rocket.emit(it)
            Timber.d("rocket ${_rocket.value}")
        }
    }
}