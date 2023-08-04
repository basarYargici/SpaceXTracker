package com.basar.spacextracker.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basar.spacextracker.domain.detail.GetRocketByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val rocketDetailUseCase: GetRocketByIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsUIState(true))
    var uiState = _uiState.asStateFlow()

    fun getRocketList(id: String) = viewModelScope.launch(Dispatchers.IO) {
        rocketDetailUseCase(id).onStart {
            _uiState.emit(
                _uiState.value.copy(isLoading = true)
            )
        }.onCompletion {
            _uiState.emit(
                _uiState.value.copy(isLoading = false)
            )
        }.catch {
            Timber.d("e $it")
        }.collect {
            _uiState.emit(
                _uiState.value.copy(isLoading = false, item = it)
            )
        }
    }
}