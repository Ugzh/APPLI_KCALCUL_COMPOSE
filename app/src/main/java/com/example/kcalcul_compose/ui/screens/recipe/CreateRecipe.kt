package com.example.kcalcul_compose.ui.screens.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.ui.shared_component.EditTextSharedComponent
import com.example.kcalcul_compose.ui.shared_component.TitleSharedComponent

@Preview(showBackground = true)
@Composable
fun CreateRecipePreview(){
    CreateRecipeContent()
}

@Composable
fun CreateRecipeContent(){
    val context = LocalContext.current
    Column {
        TitleSharedComponent(text = context.getString(R.string.submit_a_meal))
        Text(text = context.getString(R.string.name))
        EditTextSharedComponent(placeholderStr = "")
        Text(text = context.getString(R.string.ingredient))

    }
}