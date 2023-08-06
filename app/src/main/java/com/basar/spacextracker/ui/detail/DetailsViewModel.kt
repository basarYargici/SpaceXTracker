package com.basar.spacextracker.ui.detail

import androidx.lifecycle.ViewModel
import com.basar.spacextracker.domain.detail.GetRocketByIdUseCase
import com.basar.spacextracker.ext.launchViewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val rocketDetailUseCase: GetRocketByIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsUIState(true))
    var uiState = _uiState.asStateFlow()

    fun getRocketDetailById(id: String) = launchViewModelScope {
        rocketDetailUseCase(id).onStart {
            _uiState.emit(
                _uiState.value.copy(isLoading = true)
            )
        }.onCompletion {
            _uiState.emit(
                _uiState.value.copy(isLoading = false)
            )
        }.collect {
            _uiState.emit(
                _uiState.value.copy(isLoading = false, item = it)
            )
        }
    }
}