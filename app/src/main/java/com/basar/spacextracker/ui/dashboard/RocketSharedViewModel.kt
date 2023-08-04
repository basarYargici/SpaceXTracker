package com.basar.spacextracker.ui.dashboard

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class RocketSharedViewModel @Inject constructor() : ViewModel() {

    var deletedItemId: MutableSharedFlow<String> = MutableSharedFlow()
}