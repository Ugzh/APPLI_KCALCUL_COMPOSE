package com.example.kcalcul_compose.ui.shared_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.ui.theme.BorderColor

@Preview(showBackground = true)
@Composable
fun IngredientSharedComponentPreview(){
    IngredientSharedComponent("test", 10, 5, "kg", {s ->})
}

@Composable
fun IngredientSharedComponent(
    ingredient: String, kcal: Int, qty: Int, unit: String,
    deleteItem: (String)-> Unit){
    val context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextViewSharedComponentContent(name = ingredient)
        TextViewSharedComponentContent(name = kcal.toString())
        TextViewSharedComponentContent(name = qty.toString())
        TextViewSharedComponentContent(name = unit)
        Icon(
            imageVector = Icons.Default.DeleteOutline,
            contentDescription = context.getString(R.string.delete),
            Modifier.clickable {
                deleteItem(ingredient)
            }
        )
    }
}



@Preview(showBackground = true)
@Composable
fun IngredientEditsTextsPreview(){
    IngredientEditsTextsContent(addIngredient = {s, s2, s3, s4 ->  })
}

@Composable
fun IngredientEditsTextsContent(addIngredient: (String, String, String, String) -> Unit){
    val context = LocalContext.current

    var ingredient by remember {
        mutableStateOf("")
    }
    var kcal by remember {
        mutableStateOf("")
    }
    var qty by remember {
        mutableStateOf("")
    }
    var unit by remember {
        mutableStateOf("")
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        EditTextSharedComponent(
            placeholderStr = context.getString(R.string.ingredient),
            textToEdit = ingredient,
            modifier = Modifier
                .weight(0.3f)
                .padding(end = 4.dp),
            onChangeText = {
                if(it.length < 20)
                    ingredient = it
            }
        )
        EditTextSharedComponent(
            placeholderStr = context.getString(R.string.kcal_100g),
            keyboardType = KeyboardType.Number,
            textToEdit = kcal,
            modifier = Modifier
                .weight(0.3f)
                .padding(end = 4.dp),
                onChangeText = {
                    if(it.length < 6)
                        kcal = it
                }
        )
        EditTextSharedComponent(
            placeholderStr = context.getString(R.string.quantity_shortcut),
            textToEdit = qty,
            keyboardType = KeyboardType.Number,
            modifier = Modifier
                .weight(0.2f)
                .padding(end = 4.dp),
                onChangeText = {
                    if(it.length < 5)
                        qty = it
                }

        )
        EditTextSharedComponent(
            placeholderStr = context.getString(R.string.unit),
            textToEdit = unit,
            modifier = Modifier
                .weight(0.2f)
                .padding(end = 4.dp),
                onChangeText = {
                    if(it.length < 10)
                        unit = it
                }
        )
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = context.getString(R.string.add),
            modifier = Modifier
                .weight(0.05f)
                .clickable {
                    addIngredient(ingredient, kcal, qty, unit)
                    ingredient = ""
                    kcal = ""
                    qty = ""
                    unit = ""
                }
        )
    }
}
