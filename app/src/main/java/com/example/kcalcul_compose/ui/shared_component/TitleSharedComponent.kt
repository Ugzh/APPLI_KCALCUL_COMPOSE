package com.example.kcalcul_compose.ui.shared_component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun TitleSharedComponentPreview(
    modifier: Modifier = Modifier,
    text: String = "Ugo",
){
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier.padding(top = 40.dp)
    )
}

@Composable
fun TitleSharedComponent(
    modifier: Modifier = Modifier,
    text: String,
){
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier.padding(top = 40.dp))
}