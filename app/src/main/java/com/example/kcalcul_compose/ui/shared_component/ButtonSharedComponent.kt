package com.example.kcalcul_compose.ui.shared_component

import androidx.compose.foundation.background
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun ButtonSharedComponentPreview(
    modifier: Modifier = Modifier,
    btnText: String = "Ugo",
){
  Button(onClick = { /*TODO*/ }, content = { Text(text = btnText)})
}

@Composable
fun ButtonSharedComponent(
    modifier: Modifier = Modifier,
    btnText: String = "Ugo",
    onClickAction : () -> Unit
){
  Button(onClick = { onClickAction() }, content = { Text(text = btnText)}, modifier = modifier)
}