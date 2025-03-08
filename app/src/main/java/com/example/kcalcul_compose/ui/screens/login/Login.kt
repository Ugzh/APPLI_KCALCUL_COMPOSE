package com.example.kcalcul_compose.ui.screens.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.ui.navigation.Screen
import com.example.kcalcul_compose.ui.shared_component.ButtonSharedComponent
import com.example.kcalcul_compose.ui.shared_component.EditTextSharedComponent
import com.example.kcalcul_compose.ui.shared_component.Lucas
import com.example.kcalcul_compose.ui.shared_component.TitleSharedComponent

@Preview(showBackground = true)
@Composable
fun SignInPreview(){
    SignInContent(logUser = {s, s2 -> }, noAccountNavigation = {}, true)
}

@Composable

fun SignInContent(
    logUser: (String, String) -> Unit,
    noAccountNavigation: () -> Unit,
    isLoading: Boolean
){
    val context = LocalContext.current

    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    Box(Modifier.fillMaxSize()) {
        TitleSharedComponent(
            text = context.getString(R.string.sign_in),
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Column(
            Modifier
                .align(Alignment.Center)
                .padding(horizontal = 60.dp)) {
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.email),
                textToEdit = email,
                onChangeText = {
                    email = it
                }
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.password),
                isPasswordField = true,
                textToEdit = password,
                onChangeText = {
                    password = it
                }
            )
            Text(
                text = context.getString(R.string.forget_password),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 3.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp)
            ) {
                Button(onClick = {
                    logUser(email, password)
                }) {
                    Text(text = context.getString(R.string.sign_in))
                }
                Text(
                    text = context.getString(R.string.no_account),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .clickable { noAccountNavigation() }
                )
                if(isLoading)
                    CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun SignInScreen(navController: NavController, vm: LoginViewModel) {
    val context = LocalContext.current

    val isLoading by vm.progressBarLoadingStateFlow.collectAsState()

    LaunchedEffect(key1 = true) {
        vm.userLoggedSharedFlow.collect{
            if(it)
                navController.navigate(Screen.CreateMeal.route)
        }
    }

    LaunchedEffect(key1 = true) {
        vm.userMessageLiveData.collect{
            Toast.makeText(context, context.getString(it), Toast.LENGTH_SHORT).show()
        }
    }

    SignInContent(logUser = {email, password ->
        vm.logUser(email, password)
    }, noAccountNavigation = {
        navController.navigate(Screen.Register.route)
    }, isLoading)
}