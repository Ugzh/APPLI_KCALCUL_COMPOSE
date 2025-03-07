package com.example.kcalcul_compose.ui.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.network.ApiService
import com.example.kcalcul_compose.network.dtos.users.CreateUserDto
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
class RegisterViewModel @Inject constructor(
    private val apiService: ApiService,
): ViewModel() {

    private var _userMessageSharedFlow = MutableSharedFlow<Int>()
    val userMessageSharedFlow = _userMessageSharedFlow.asSharedFlow()

    private var _userCreatedSharedFlow = MutableSharedFlow<Boolean>()
    val userCreatedSharedFlow = _userCreatedSharedFlow.asSharedFlow()

    private var _progressBarLoadingStateFlow = MutableStateFlow(false)
    val progressBarLoadingStateFlow = _progressBarLoadingStateFlow.asStateFlow()

    fun createUser(firstname : String,
                   lastname: String,
                   email: String,
                   password : String,
                   confirmPassword: String,
                   dailyRequirements : String
    ) {
        val trimFirstname = firstname.trim().lowercase()
        val trimLastname = lastname.trim().lowercase()
        val trimEmail = email.trim().lowercase()
        val trimPassword = password.trim().lowercase()
        val trimConfirmPassword = confirmPassword.trim().lowercase()
        val trimDailyRequirements = dailyRequirements.trim().lowercase()

        if (
            trimFirstname.isNotEmpty()
            && trimLastname.isNotEmpty()
            && trimEmail.isNotEmpty()
            && trimPassword.isNotEmpty()
            && trimConfirmPassword.isNotEmpty()
        )
        {
            if(trimPassword == confirmPassword){
                viewModelScope.launch {
                    _progressBarLoadingStateFlow.value = true
                    try {
                        val responseRegister = withContext(Dispatchers.IO){
                            apiService.createUser(
                                CreateUserDto(id=0,
                                firstname,
                                lastname,
                                email,password,
                                null,
                                trimDailyRequirements.toInt())
                            )
                        }
                        _progressBarLoadingStateFlow.value = false

                        val body = responseRegister?.body()

                        when{
                            responseRegister == null ->
                                R.string.no_response_database

                            responseRegister.isSuccessful && body != null -> {
                                _userCreatedSharedFlow.emit(true)
                                R.string.user_created
                            }

                            responseRegister.code() == 409 ->
                                R.string.email_already_register

                            else -> return@launch
                        }.let {
                            _userMessageSharedFlow.emit(it)
                        }
                    } catch (ce: ConnectException){
                        _progressBarLoadingStateFlow.value = false
                        viewModelScope.launch {
                            _userMessageSharedFlow.emit(R.string.no_response_database)
                        }
                    }
                }
            } else
                viewModelScope.launch {
                    _userMessageSharedFlow.emit(R.string.password_not_match)
                }
        } else
            viewModelScope.launch {
                _userMessageSharedFlow.emit(R.string.fill_fields)
            }
    }
}