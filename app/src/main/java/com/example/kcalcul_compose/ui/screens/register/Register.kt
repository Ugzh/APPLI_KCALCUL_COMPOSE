package com.example.kcalcul_compose.ui.screens.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.ui.navigation.Screen
import com.example.kcalcul_compose.ui.shared_component.ButtonSharedComponent
import com.example.kcalcul_compose.ui.shared_component.EditTextSharedComponent
import com.example.kcalcul_compose.ui.shared_component.TitleSharedComponent
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.collect

@Preview(showBackground = true)
@Composable
fun RegisterPreview(){
    RegisterContent(registerUser = {s1, s2, s3, s4, s5, i ->}, {}, true)
}

@Composable
fun RegisterContent(
    registerUser : (String, String, String, String, String, String)-> Unit,
    accountNavigation: () -> Unit,
    isLoading: Boolean
){
    val context = LocalContext.current
    var firstname by remember {
        mutableStateOf("")
    }
    var lastname by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
    var dailyRequirements by remember {
        mutableStateOf("")
    }
    Box(Modifier.fillMaxSize()) {
        TitleSharedComponent(
            text = context.getString(R.string.sign_up),
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Column(
            Modifier
                .align(Alignment.Center)
                .padding(horizontal = 60.dp)) {
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.firstname),
                onChangeText = {
                    firstname = it
                }
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.lastname),
                onChangeText = {
                    lastname = it
                }
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.email),
                keyboardType = KeyboardType.Email,
                onChangeText = {
                    email = it
                }
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.password),
                isPasswordField = true,
                keyboardType = KeyboardType.Password,
                onChangeText = {
                    password = it
                }
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.confirm_password),
                isPasswordField = true,
                onChangeText = {
                    confirmPassword = it
                }
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.daily_requirements),
                keyboardType = KeyboardType.Number,
                onChangeText = {
                    dailyRequirements = it
                }
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp)) {
                ButtonSharedComponent(
                    btnText = context.getString(R.string.create_account),
                    modifier = Modifier,
                    onClickAction = {
                        registerUser(
                            firstname,
                            lastname,
                            email,
                            password,
                            confirmPassword,
                            dailyRequirements
                        )
                    }
                )
                Text(
                    text = context.getString(R.string.already_account),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .clickable {
                            accountNavigation()
                        }
                )
                if(isLoading)
                    CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun RegisterScreen(navController: NavController, vm: RegisterViewModel){
    val context = LocalContext.current
    var isLoading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        vm.progressBarLoadingSharedFlow.collect{
            isLoading = it
        }
    }

    LaunchedEffect(key1 = true) {
        vm.userMessageSharedFlow.collect{
            Toast.makeText(context, context.getString(it), Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = true) {
        vm.userCreatedSharedFlow.collect{
            if(it)
                navController.navigate(Screen.Login.route)
        }
    }

    RegisterContent(
        accountNavigation = { navController.navigate(Screen.Login.route) },
        registerUser = { firstname, lastname, email, password, confirmPassword, dailyRequirements ->
            vm.createUser(firstname, lastname,email, password, confirmPassword, dailyRequirements)
        },
        isLoading = isLoading
    )
}