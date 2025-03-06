package com.example.kcalcul_compose.ui.screens.edit

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
    val userMessageLiveData = _userMessageSharedFlow.asSharedFlow()

    private var _updatedUserSharedFlow = MutableSharedFlow<Boolean>()
    val updatedUserSharedFlow = _updatedUserSharedFlow.asSharedFlow()

    private var _progressBarLoadingSharedFlow = MutableSharedFlow<Boolean>()
    val progressBarLoadingSharedFlow = _progressBarLoadingSharedFlow.asSharedFlow()

    private var _userStateFlow: MutableStateFlow<User?> = MutableStateFlow(null)
    val userStateFlow = _userStateFlow.asStateFlow()

    init {
        getUser()
    }

    private fun getUser(){
        viewModelScope.launch {
            _progressBarLoadingSharedFlow.emit(true)
            try {
                val responseEdit = withContext(Dispatchers.IO){
                    apiService.getUser(
                        myPrefs.token!!,
                        myPrefs.userId
                    )
                }
                _progressBarLoadingSharedFlow.emit(false)

                val body = responseEdit?.body()

                when{
                    responseEdit == null ->
                        R.string.no_response_database

                    responseEdit.isSuccessful && body != null -> {
                        with(body){
                            _userStateFlow.emit(User(firstname, lastname, email, profileUrlPage))
                        }
                    }

                    responseEdit.code() == 400 -> R.string.user_not_found
                    else -> return@launch
                }
            } catch (ce: ConnectException){
                _progressBarLoadingSharedFlow.emit(false)
                _userMessageSharedFlow.emit(R.string.no_response_database)
            }
        }
    }


    fun updateUser() {
        _userStateFlow.value?.let {
            val trimDailyRequirements = it.dailyRequirements.toString()
            val trimFirstname = it.firstname.trim()
            val trimLastname = it.lastname.trim()
            val trimEmail = it.email.trim()

            if(trimFirstname.isNotEmpty()
                && trimLastname.isNotEmpty()
                && trimEmail.isNotEmpty()
                && trimDailyRequirements.isNotEmpty()
            )
            {
                viewModelScope.launch {
                    _progressBarLoadingSharedFlow.emit(true)
                    try {
                        val responseEdit = withContext(Dispatchers.IO){
                            apiService.updateUser(
                                myPrefs.userId,
                                UpdateUserDto(trimFirstname, trimLastname, trimEmail, trimDailyRequirements.toInt()),
                                myPrefs.token!!
                            )
                        }
                        _progressBarLoadingSharedFlow.emit(false)

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
                        _progressBarLoadingSharedFlow.emit(false)
                        _userMessageSharedFlow.emit(R.string.no_response_database)
                    }
                }
            } else
                viewModelScope.launch {
                    _userMessageSharedFlow.emit(R.string.fill_all_fields_and_requirements)
                }
        }
    }
}




