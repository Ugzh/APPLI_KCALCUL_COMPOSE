package com.example.kcalcul_compose.ui.screens

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
                    .padding(top = 40.dp)
                    .align(Alignment.CenterHorizontally)){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    ButtonSharedComponent(btnText = "Create", modifier = Modifier)
                    Text(text = "I have an account")
                }
            }
        }

    }
}