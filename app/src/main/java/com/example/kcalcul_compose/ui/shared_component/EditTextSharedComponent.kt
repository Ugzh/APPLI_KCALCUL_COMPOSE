package com.example.kcalcul_compose.ui.shared_component

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun EditTextSharedComponentPreview(
    modifier: Modifier= Modifier,
    textToEdit: String = "Ugo",
    placeholderStr: String= "Firstname",
    textSize: TextUnit = 14.sp
){
    var updatedText by remember {
        mutableStateOf(textToEdit)
    }
    OutlinedTextField(
        modifier = modifier,
        value = updatedText,
        onValueChange = { updatedText = it },
        textStyle = TextStyle(fontSize = textSize),
        label = {
            Text(text = placeholderStr)
        },
    )
}

@Composable
fun EditTextSharedComponent(
    modifier: Modifier= Modifier,
    placeholderStr: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordField: Boolean = false,
    textSize: TextUnit = 14.sp,
    textToEdit: String = "",
    onChangeText: (String) -> Unit,
){
    OutlinedTextField(
        modifier = modifier,
        value = textToEdit,
        onValueChange = onChangeText,
        label = {
            Text(text = placeholderStr, fontSize = textSize)
        },
        textStyle = TextStyle(fontSize = textSize),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        maxLines = 1,
        visualTransformation =
        if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Composable
fun Lucas(
    value: String,
    labelText: String,
    onValueChange: (String) -> Unit,
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = labelText)
        },

    )

}

@Preview(showBackground = true)
@Composable
fun Test(){
    var state by remember {
        mutableStateOf("")
    }
    Column {
        Lucas(labelText = "Lucas", value = state, onValueChange = {state = it})
        Button(onClick = {Log.d("test", "$state test")}) {
        }
    }

}