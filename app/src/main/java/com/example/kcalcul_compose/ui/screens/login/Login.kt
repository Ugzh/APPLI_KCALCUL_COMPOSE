package com.example.kcalcul_compose.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kcalcul_compose.ui.shared_component.ButtonSharedComponent
import com.example.kcalcul_compose.ui.shared_component.EditTextSharedComponent
import com.example.kcalcul_compose.ui.shared_component.TitleSharedComponent

@Preview(showBackground = true)
@Composable
fun SignInPreview(){
    Box(Modifier.fillMaxSize()) {
        TitleSharedComponent(text = "Sign in", modifier = Modifier.align(Alignment.TopCenter))
        Column(
            Modifier
                .align(Alignment.Center)
                .padding(horizontal = 60.dp)) {
            EditTextSharedComponent(placeholderStr = "Email")
            EditTextSharedComponent(
                placeholderStr = "Password",
                isPasswordField = true
            )
            Text(text = "I forget my password",
                Modifier
                    .align(Alignment.End)
                    .padding(top = 3.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp)
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Sign in")
                }
                Text(text = "I don't have an account", Modifier.padding(top= 10.dp))
            }
        }
    }
}

@Composable
fun SignInContent(){
    Box(Modifier.fillMaxSize()) {
        TitleSharedComponent(text = "Sign in", modifier = Modifier.align(Alignment.TopCenter))
        Column(
            Modifier
                .align(Alignment.Center)
                .padding(horizontal = 60.dp)) {
            EditTextSharedComponent(placeholderStr = "Email")
            EditTextSharedComponent(
                placeholderStr = "Password",
                isPasswordField = true
            )
            Text(text = "I forget my password",
                Modifier
                    .align(Alignment.End)
                    .padding(top = 3.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp)
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Sign in")
                }
                Text(text = "I don't have an account", Modifier.padding(top= 10.dp))
            }
        }
    }
}

@Composable
fun SignInScreen(){
    val vm: LoginViewModel = viewModel()
    SignInContent()
}