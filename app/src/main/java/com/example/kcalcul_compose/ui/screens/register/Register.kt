package com.example.kcalcul_compose.ui.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.ui.shared_component.ButtonSharedComponent
import com.example.kcalcul_compose.ui.shared_component.EditTextSharedComponent
import com.example.kcalcul_compose.ui.shared_component.TitleSharedComponent

@Preview(showBackground = true)
@Composable
fun RegisterPreview(){
    RegisterContent()
}

@Composable
fun RegisterContent(){
    val context = LocalContext.current
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
                placeholderStr = context.getString(R.string.firstname)
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.lastname)
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.email)
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.password),
                isPasswordField = true
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.confirm_password),
                isPasswordField = true
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.daily_requirements),
                keyboardType = KeyboardType.Number
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp)) {
                ButtonSharedComponent(
                    btnText = context.getString(R.string.create_account),
                    modifier = Modifier
                )
                Text(
                    text = context.getString(R.string.already_account),
                    modifier =Modifier.padding(top= 10.dp)
                )
            }
        }
    }
}

@Composable
fun RegisterScreen(){
    RegisterContent()
}