package com.example.kcalcul_compose.ui.shared_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.ui.theme.BorderColor

@Preview(showBackground = true)
@Composable
fun IngredientSharedComponentPreview(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextViewIngredient(name = "Ingredient")
        TextViewIngredient(name = "Kcal/100g")
        TextViewIngredient(name = "Qty")
        TextViewIngredient(name = "Unit")
        Image(
            painter = painterResource(id = R.drawable.trash),
            contentDescription = "trash",
            Modifier.width(25.dp).height(25.dp))
    }
}

@Composable
fun IngredientSharedComponent(ingredient: String, kcal: Int, qty: Int, unit: String){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextViewIngredient(name = ingredient)
        TextViewIngredient(name = kcal.toString())
        TextViewIngredient(name = qty.toString())
        TextViewIngredient(name = unit)
        Image(
            painter = painterResource(id = R.drawable.trash),
            contentDescription = "trash",
            Modifier.width(25.dp).height(25.dp)
        )
    }
}

@Composable
fun TextViewIngredient(name: String, modifier : Modifier = Modifier){
    Text(text = name,
        modifier = modifier
            .padding(3.dp)
            .border(
                width = 1.dp,
                brush = SolidColor(BorderColor),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(
                top =10.dp,
                bottom = 10.dp,
                start = 20.dp,
                end = 20.dp),
        maxLines = 1
    )

}