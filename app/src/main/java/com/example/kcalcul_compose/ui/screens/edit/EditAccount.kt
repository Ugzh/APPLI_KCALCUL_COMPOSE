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
fun EditAccountPreview(){
    EditAccountContent()
}

@Composable
fun EditAccountContent(){
    val context = LocalContext.current
    Box(Modifier.fillMaxSize()) {
        TitleSharedComponent(
            text = context.getString(R.string.edit_account),
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Column(
            Modifier
                .align(Alignment.Center)
                .padding(horizontal = 60.dp)) {
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.firstname),
                onChangeText = {

                }
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.lastname),
                onChangeText = {

                }
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.email),
                onChangeText = {

                }
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.daily_requirements),
                keyboardType = KeyboardType.Number,
                onChangeText = {

                }
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth()
            ) {
                ButtonSharedComponent(
                    btnText = context.getString(R.string.back),
                    modifier = Modifier,
                    onClickAction = {}
                )
                ButtonSharedComponent(
                    btnText = context.getString(R.string.validate),
                    modifier = Modifier,
                    onClickAction = {}
                )
            }
        }
    }
}

@Composable
fun EditAccountScreen(){
    EditAccountContent()
}