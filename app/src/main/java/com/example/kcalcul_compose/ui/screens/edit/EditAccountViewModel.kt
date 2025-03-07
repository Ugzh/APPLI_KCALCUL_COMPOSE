package com.example.kcalcul_compose.ui.screens.edit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kcalcul_compose.network.ApiService
import com.example.kcalcul_compose.network.MyPrefs
import com.example.kcalcul_compose.network.dtos.users.UpdateUserDto
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.utils.User
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
class EditAccountViewModel @Inject constructor(
    private val apiService: ApiService,
    private val myPrefs: MyPrefs
) : ViewModel() {

    private var _userMessageSharedFlow = MutableSharedFlow<Int>()
    val userMessageSharedFlow = _userMessageSharedFlow.asSharedFlow()

    private var _updatedUserSharedFlow = MutableSharedFlow<Boolean>()
    val updatedUserSharedFlow = _updatedUserSharedFlow.asSharedFlow()

    private var _progressBarLoadingStateFlow = MutableStateFlow(false)
    val progressBarLoadingStateFlow = _progressBarLoadingStateFlow.asStateFlow()

    private var _userStateFlow: MutableStateFlow<User?> = MutableStateFlow(null)
    val userStateFlow = _userStateFlow.asStateFlow()

    init {
        getUser()
    }

    private fun getUser(){
        viewModelScope.launch {
            _progressBarLoadingStateFlow.value = true
            try {
                val responseEdit = withContext(Dispatchers.IO){
                    apiService.getUser(
                        myPrefs.token!!,
                        myPrefs.userId
                    )
                }
                _progressBarLoadingStateFlow.value = false

                val body = responseEdit?.body()

                when{
                    responseEdit == null ->
                        R.string.no_response_database

                    responseEdit.isSuccessful && body != null -> {
                        with(body){
                            _userStateFlow.value =
                                User(firstname, lastname, email, profileUrlPage, dailyRequirements)
                        }
                    }

                    responseEdit.code() == 400 -> R.string.user_not_found
                    else -> return@launch
                }
            } catch (ce: ConnectException){
                _progressBarLoadingStateFlow.value = false
                _userMessageSharedFlow.emit(R.string.no_response_database)
            }
        }
    }


    fun updateUser(firstname: String, lastname: String, email: String, dailyRequirements: Int) {
        val trimDailyRequirements = dailyRequirements.toString()
        val trimFirstname = firstname.trim()
        val trimLastname = lastname.trim()
        val trimEmail = email.trim()

        if(trimFirstname.isNotEmpty()
            && trimLastname.isNotEmpty()
            && trimEmail.isNotEmpty()
            && trimDailyRequirements.isNotEmpty()
        ){
            viewModelScope.launch {
                _progressBarLoadingStateFlow.value = true
                try {
                    val responseEdit = withContext(Dispatchers.IO){
                        apiService.updateUser(
                            myPrefs.userId,
                            UpdateUserDto(
                                trimFirstname,
                                trimLastname,
                                trimEmail,
                                trimDailyRequirements.toInt()
                            ),
                            myPrefs.token!!
                        )
                    }
                    _progressBarLoadingStateFlow.value = false

                    val body = responseEdit?.body()

                    when{
                        responseEdit == null ->
                            R.string.no_response_database

                        responseEdit.isSuccessful && body != null -> {
                            _updatedUserSharedFlow.emit(true)
                            R.string.user_updated
                        }

                        responseEdit.code() == 400 ->
                            R.string.user_not_updated

                        else -> return@launch
                    }.let {
                        _userMessageSharedFlow.emit(it)
                    }
                } catch (ce: ConnectException){
                    _progressBarLoadingStateFlow.value = false
                    _userMessageSharedFlow.emit(R.string.no_response_database)
                }
            }
        } else
            viewModelScope.launch {
                _userMessageSharedFlow.emit(R.string.fill_all_fields_and_requirements)
            }
    }
}




