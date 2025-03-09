package com.example.kcalcul_compose.ui.shared_component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kcalcul_compose.ui.theme.BorderColor

@Preview(showBackground = true)
@Composable
fun TextViewSharedComponentPreview(){
    TextViewSharedComponentContent()
}

@Composable
fun TextViewSharedComponentContent(
    name: String= "Poulet",
    modifier : Modifier = Modifier
){
    Text(text = name,
        modifier = modifier
            .padding(3.dp)
            .border(
                width = 1.dp,
                brush = SolidColor(BorderColor),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(
                top = 10.dp,
                bottom = 10.dp,
                start = 20.dp,
                end = 20.dp
            ),
        maxLines = 1
    )
}