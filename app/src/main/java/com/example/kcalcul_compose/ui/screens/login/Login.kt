package com.example.kcalcul_compose.ui.screens.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.ui.navigation.NavigationItem
import com.example.kcalcul_compose.ui.shared_component.ButtonSharedComponent
import com.example.kcalcul_compose.ui.shared_component.EditTextSharedComponent
import com.example.kcalcul_compose.ui.shared_component.TitleSharedComponent

@Preview(showBackground = true)
@Composable
fun SignInPreview(){
    SignInContent()
}

@Composable
fun SignInContent(){
    val context = LocalContext.current
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
                placeholderStr = context.getString(R.string.email)
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.password),
                isPasswordField = true
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
                Button(onClick = { /*TODO*/ }) {
                    Text(text = context.getString(R.string.sign_in))
                }
                Text(
                    text = context.getString(R.string.no_account),
                    modifier = Modifier.padding(top= 10.dp)
                )
            }
        }
    }
}

@Composable
fun SignInScreen() {
    val navController = rememberNavController()
    val vm: LoginViewModel = hiltViewModel()

    LaunchedEffect(key1 = true) {
        vm.userLoggedSharedFlow.collect{
            if(it)
                navController.navigate(NavigationItem.Home.route)
        }
    }
    SignInContent()
}