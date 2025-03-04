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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kcalcul_compose.ui.shared_component.ButtonSharedComponent
import com.example.kcalcul_compose.ui.shared_component.EditTextSharedComponent
import com.example.kcalcul_compose.ui.shared_component.TitleSharedComponent

@Preview(showBackground = true)
@Composable
fun RegisterPreview(){
    Box(Modifier.fillMaxSize()) {
        TitleSharedComponent(text = "Sign-up", modifier = Modifier.align(Alignment.TopCenter))
        Column(Modifier.align(Alignment.Center).padding(horizontal = 60.dp)) {
            EditTextSharedComponent(placeholderStr = "Firstname")
            EditTextSharedComponent(placeholderStr = "Lastname")
            EditTextSharedComponent(placeholderStr = "Email")
            EditTextSharedComponent(placeholderStr = "Password", isPasswordField = true)
            EditTextSharedComponent(placeholderStr = "Confirm password", isPasswordField = true)
            EditTextSharedComponent(
                placeholderStr = "Daily requirements",
                keyboardType = KeyboardType.Number
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp)) {
                ButtonSharedComponent(btnText = "Create", modifier = Modifier)
                Text(text = "I have an account", Modifier.padding(top= 10.dp))
            }
        }

    }
}

@Composable
fun Register(){
    Box(Modifier.fillMaxSize()) {
        TitleSharedComponent(text = "Sign-up", modifier = Modifier.align(Alignment.TopCenter))
        Column(Modifier.align(Alignment.Center)) {
            EditTextSharedComponent(placeholderStr = "Firstname")
            EditTextSharedComponent(placeholderStr = "Lastname")
            EditTextSharedComponent(placeholderStr = "Email")
            EditTextSharedComponent(placeholderStr = "Password", isPasswordField = true)
            EditTextSharedComponent(placeholderStr = "Confirm password", isPasswordField = true)
            EditTextSharedComponent(
                placeholderStr = "Daily requirements",
                keyboardType = KeyboardType.Number
            )
            Box(
                Modifier
                    .padding(top = 60.dp)
                    .align(Alignment.CenterHorizontally)){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    ButtonSharedComponent(btnText = "Create", modifier = Modifier)
                    Text(text = "I have an account", Modifier.padding(top= 10.dp))
                }
            }
        }

    }
}