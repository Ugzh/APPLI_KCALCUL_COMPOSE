package com.example.kcalcul_compose.ui.screens.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kcalcul_compose.utils.User
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow

class EditAccountViewModel {

    private var _userMessageSharedFlow = MutableSharedFlow<Int>()
    val userMessageLiveData = _userMessageSharedFlow.asSharedFlow()

    private var _updatedUserSharedFlow = MutableSharedFlow<Boolean>()
    val updatedUserSharedFlow = _updatedUserSharedFlow.asSharedFlow()

    private var _progressBarLoadingSharedFlow = MutableSharedFlow<Boolean>()
    val progressBarLoadingSharedFlow = _progressBarLoadingSharedFlow.asSharedFlow()

    /*private var _userStateFlow = MutableStateFlow<User>()
    val userLiveData
        get() = _userLiveData*/
}