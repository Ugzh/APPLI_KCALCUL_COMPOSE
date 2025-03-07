package com.example.kcalcul_compose.ui.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kcalcul_compose.network.ApiService
import com.example.kcalcul_compose.network.MyPrefs
import com.example.kcalcul_compose.network.dtos.users.LogUserDto
import com.example.kcalcul_compose.R
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService,
    private val myPrefs: MyPrefs,
): ViewModel() {

    private var _userMessageSharedFlow = MutableSharedFlow<Int>()
    val userMessageLiveData = _userMessageSharedFlow.asSharedFlow()

    private var _userLoggedSharedFlow = MutableSharedFlow<Boolean>()
    val userLoggedSharedFlow = _userLoggedSharedFlow.asSharedFlow()

    private var _progressBarLoadingStateFlow = MutableStateFlow(false)
    val progressBarLoadingStateFlow = _progressBarLoadingStateFlow.asStateFlow()


    fun logUser(email: String, password: String) {
        val trimEmail = email.trim()
        val trimPassword = password.trim()
        if(trimEmail.isNotEmpty() && trimPassword.isNotEmpty()){
            viewModelScope.launch {
                _progressBarLoadingStateFlow.value = true
                try {
                    val response = withContext(Dispatchers.IO){
                        apiService.logUser(LogUserDto(trimEmail, trimPassword))
                    }
                    _progressBarLoadingStateFlow.value = false

                    val body = response?.body()

                    when{
                        response == null ->  R.string.no_response_database
                        response.isSuccessful && body != null -> {
                            body.user.let {
                                myPrefs.userId = it.id
                                myPrefs.firstname = it.firstname
                                myPrefs.token = "Bearer ${body.token}"
                                _userLoggedSharedFlow.emit(true)
                            }
                            R.string.user_logged
                        }
                        response.code() == 404 -> R.string.user_not_found
                        else -> return@launch
                    }.let {
                        _userMessageSharedFlow.emit(it)
                    }
                } catch (ce : ConnectException){
                    _progressBarLoadingStateFlow.value = false
                    _userMessageSharedFlow.emit(R.string.no_response_database)
                }
            }
        } else
            viewModelScope.launch {
                _userMessageSharedFlow.emit(R.string.fill_fields)
            }
    }
}

