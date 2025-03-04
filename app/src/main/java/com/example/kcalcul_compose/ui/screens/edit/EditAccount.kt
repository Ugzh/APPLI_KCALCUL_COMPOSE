package com.example.kcalcul_compose.ui.screens.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun EditAccountPreview(){
    Box(Modifier.fillMaxSize()) {
        TitleSharedComponent(text = "Edit Account", modifier = Modifier.align(Alignment.TopCenter))
        Column(Modifier.align(Alignment.Center).padding(horizontal = 60.dp)) {
            EditTextSharedComponent(placeholderStr = "Firstname")
            EditTextSharedComponent(placeholderStr = "Lastname")
            EditTextSharedComponent(placeholderStr = "Email")
            EditTextSharedComponent(
                placeholderStr = "Daily requirements",
                keyboardType = KeyboardType.Number
            )
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(top = 40.dp).fillMaxWidth()) {
                ButtonSharedComponent(btnText = "Back", modifier = Modifier)
                ButtonSharedComponent(btnText = "Validate", modifier = Modifier)
            }
        }
    }
}

@Composable
fun EditAccount(){
    Box(Modifier.fillMaxSize()) {
        TitleSharedComponent(text = "Edit Account", modifier = Modifier.align(Alignment.TopCenter))
        Column(Modifier.align(Alignment.Center).padding(horizontal = 60.dp)) {
            EditTextSharedComponent(placeholderStr = "Firstname")
            EditTextSharedComponent(placeholderStr = "Lastname")
            EditTextSharedComponent(placeholderStr = "Email")
            EditTextSharedComponent(
                placeholderStr = "Daily requirements",
                keyboardType = KeyboardType.Number
            )
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(top = 40.dp).fillMaxWidth()) {
                ButtonSharedComponent(btnText = "Back", modifier = Modifier)
                ButtonSharedComponent(btnText = "Validate", modifier = Modifier)
            }
        }
    }
}