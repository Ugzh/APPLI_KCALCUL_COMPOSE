package com.example.kcalcul_compose.ui.screens.createMeal

import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.network.dtos.recipes.RecipeDto
import com.example.kcalcul_compose.ui.shared_component.DropDownMenuSharedComponentContent
import com.example.kcalcul_compose.ui.shared_component.EditTextSharedComponent
import com.example.kcalcul_compose.ui.shared_component.MealItemRecipeContent
import com.example.kcalcul_compose.ui.shared_component.MealItemRecipeSelectedContent

import com.example.kcalcul_compose.ui.theme.BorderColor
import com.example.kcalcul_compose.ui.theme.Purple40
import com.example.kcalcul_compose.utils.RecipeCustom
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun CreateMealPreview(){
    CreateMealContent(
        listOfRecipesSelectedByUser = emptyList(),
        listOfRecipesFromDB = emptyList(),
        onDeleteRecipe = {},
        addRecipeOnClick = {_,_ ->}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateMealContent(
    listOfRecipesSelectedByUser: List<RecipeCustom>,
    onDeleteRecipe : (RecipeCustom) -> Unit,
    listOfRecipesFromDB: List<RecipeDto>,
    addRecipeOnClick: (RecipeDto, String) -> Unit
){
    val context = LocalContext.current
    var showDate by remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState()
    var selectedDate by remember {
        mutableLongStateOf(System.currentTimeMillis())
    }
    var strDate by remember {
        mutableStateOf("")
    }
    val listOfNotificationChoice = listOf(R.string.yes, R.string.no)
    var notificationChoice by remember {
        mutableIntStateOf(listOfNotificationChoice.get(1))
    }

    val lisOfMealType = listOf(
        R.string.breakfast,
        R.string.lunch,
        R.string.snack,
        R.string.dinner
    )

    var mealTypeChoice by remember {
        mutableIntStateOf(lisOfMealType.get(0))
    }

    var recipesChoice by remember {
        mutableStateOf(listOfRecipesFromDB.get(0))

    }

    var quantityByRecipe by remember {
        mutableStateOf("")
    }

    Column(Modifier.padding(horizontal = 10.dp)){
        Text(text = context.getString(R.string.date))
        Spacer(modifier = Modifier.size(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = strDate.ifEmpty { context.getString(R.string.pick_date) },
                modifier = Modifier
                    .padding(3.dp)
                    .border(
                        width = 1.dp,
                        brush = SolidColor(Purple40),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(
                        top = 10.dp,
                        bottom = 10.dp,
                        start = 20.dp,
                        end = 20.dp
                    )
                    .clickable {
                        showDate = true
                    },
                maxLines = 1
            )
            Spacer(modifier = Modifier.width(10.dp))
            if(selectedDate > System.currentTimeMillis())
                DropDownMenuSharedComponentContent(
                    value = context.getString(notificationChoice),
                    listOfItemStringId = listOfNotificationChoice,
                    labelText = context.getString(R.string.be_notified),
                    getItemSelected = {
                        notificationChoice = it
                    }
                )
        }
        Spacer(modifier = Modifier.size(20.dp))
        DropDownMenuSharedComponentContent(
            value = context.getString(mealTypeChoice),
            listOfItemStringId = lisOfMealType,
            labelText = context.getString(R.string.meal_type),
            getItemSelected = { mealTypeChoice = it }
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = context.getString(R.string.select_recipe))
        MealItemRecipeContent(
            listOfItems = listOfRecipesFromDB,
            valueDropDownRecipe = recipesChoice,
            valueQty = quantityByRecipe,
            getItemSelected = {
                recipesChoice = listOfRecipesFromDB.get(it)},
            onChangeText = {quantityByRecipe = it},
            addOnClick = addRecipeOnClick
        )
        LazyColumn {
            items(items = listOfRecipesSelectedByUser){
                Row {
                    MealItemRecipeSelectedContent(
                        it,
                        deleteOnClick = onDeleteRecipe
                        )
                }
            }
        }

        if(showDate)
            DatePickerDialog(
                onDismissRequest = {  },
                confirmButton = {
                    TextButton(onClick = {
                        showDate = false
                        selectedDate = datePickerState.selectedDateMillis!!
                        strDate =
                            SimpleDateFormat(
                                "dd/MMM/yyyy", Locale.getDefault()
                            ).format(Date(datePickerState.selectedDateMillis!!))
                    }) {
                        Text(context.getString(R.string.ok))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDate = false }) {
                        Text(context.getString(R.string.cancel))
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
    }

}

@Composable
fun CreateMealScreen(navController: NavController, vm: CreateMealViewModel){
    val foodBeveragesFromDBList by vm.foodsBeveragesStateFlow.collectAsState()
    val recipesFromDBList by vm.recipesStateFlow.collectAsState()

    val recipeSelectedByUserList by vm.recipeSelectedByUser.collectAsState()

    if(recipesFromDBList.size > 1 ){
        CreateMealContent(
            listOfRecipesSelectedByUser = recipeSelectedByUserList,
            listOfRecipesFromDB = recipesFromDBList,
            onDeleteRecipe = { vm.removeRecipe(it.recipe) },
            addRecipeOnClick = {recipe, qty -> vm.addRecipe(recipe, qty)}
        )
    }
}
