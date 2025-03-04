package com.example.kcalcul_compose.ui.screens.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class RegisterViewModel: ViewModel() {

    private var _userMessageSharedFlow = MutableSharedFlow<Int>()
    val userMessageLiveData = _userMessageSharedFlow.asSharedFlow()

    private var _userCreatedSharedFlow = MutableSharedFlow<Boolean>()
    val userCreatedSharedFlow = _userCreatedSharedFlow.asSharedFlow()

    private var _progressBarLoadingSharedFlow = MutableSharedFlow<Boolean>()
    val progressBarLoadingSharedFlow = _progressBarLoadingSharedFlow.asSharedFlow()
}