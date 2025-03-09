package com.example.kcalcul_compose.ui.shared_component

import android.util.Log
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.network.dtos.foodsBeverages.FoodBeverageResponseDto
import com.example.kcalcul_compose.network.dtos.recipes.RecipeDto

@Preview(showBackground = true)
@Composable
fun DropDownMenuSharedComponentPreview(){
    val context = LocalContext.current

    DropDownMenuSharedComponentContent(
        value = "s",
        listOf(R.string.starter, R.string.main_course, R.string.dessert),
        labelText = context.getString(R.string.dish_category),
        getItemSelected = {i ->}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuSharedComponentContent(
    value: String,
    listOfItemStringId: List<Int> = emptyList(),
    listOfRecipes: List<RecipeDto> = emptyList(),
    listOfFoodBeverages: List<FoodBeverageResponseDto> = emptyList(),
    listOfItems: List<String> = emptyList(),
    labelText: String = "",
    getItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current

    var isExpanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            isExpanded = it
        },
        modifier = modifier
    ){
        TextField(
            value = value,
            onValueChange = {},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                           },
            readOnly = true,
            label = {
                Text(
                    text = labelText
                )
            },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            if(listOfItemStringId.isNotEmpty())
                listOfItemStringId.forEach{ category ->
                    DropdownMenuItem(
                        text = {
                            Text(text = context.getString(category))
                        },
                        onClick = {
                            getItemSelected(category)
                            isExpanded = false
                        }
                    )
                }
            if(listOfRecipes.isNotEmpty())
                listOfRecipes.forEachIndexed(){ i, recipe ->
                    DropdownMenuItem(
                        text = {
                            Text(text = recipe.name)
                        },
                        onClick = {
                            getItemSelected(i)
                            isExpanded = false
                        }
                    )
                }
            if(listOfItems.isNotEmpty())
                listOfRecipes.forEachIndexed(){ i, recipe ->
                    DropdownMenuItem(
                        text = {
                            Text(text = recipe.name)
                        },
                        onClick = {
                            getItemSelected(i)
                            isExpanded = false
                        }
                    )
                }
            if(listOfFoodBeverages.isNotEmpty())
                listOfFoodBeverages.forEachIndexed(){ i, food ->
                    DropdownMenuItem(
                        text = {
                            Text(text = food.name)
                        },
                        onClick = {
                            getItemSelected(i)
                            isExpanded = false
                        }
                    )
                }
        }
    }
}