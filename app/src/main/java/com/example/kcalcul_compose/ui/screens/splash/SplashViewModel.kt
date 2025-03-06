package com.example.kcalcul_compose.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kcalcul_compose.network.MyPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val myPrefs: MyPrefs
): ViewModel() {

    private var _userAlreadyLogSharedFlow = MutableSharedFlow<Boolean>()
    val userAlreadyLogSharedFlow = _userAlreadyLogSharedFlow.asSharedFlow()

    init{
        viewModelScope.launch{
            delay(1000)
            _userAlreadyLogSharedFlow.emit(
                myPrefs.token != null
            )
        }
    }
}