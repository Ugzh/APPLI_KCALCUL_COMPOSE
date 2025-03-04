package com.example.kcalcul_compose.ui.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class LoginViewModel: ViewModel() {
    private var _userMessageSharedFlow = MutableSharedFlow<Int>()
    val userMessageLiveData = _userMessageSharedFlow.asSharedFlow()

    private var _userLoggedSharedFlow = MutableSharedFlow<Boolean>()
    val userLoggedSharedFlow = _userLoggedSharedFlow.asSharedFlow()

    private var _progressBarLoadingSharedFlow = MutableSharedFlow<Boolean>()
    val progressBarLoadingSharedFlow = _progressBarLoadingSharedFlow.asSharedFlow()

}