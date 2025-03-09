package com.example.kcalcul_compose.ui.screens.createRecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kcalcul_compose.network.ApiService
import com.example.kcalcul_compose.network.MyPrefs
import com.example.kcalcul_compose.network.dtos.recipes.CreateRecipeDto
import com.example.kcalcul_compose.network.dtos.foodsBeverages.FoodBeverageDto
import com.example.kcalcul_compose.network.dtos.foodsBeverages.UnitDto
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.utils.FoodBeverageCustom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class CreateRecipeViewModel @Inject constructor(
    private val apiService: ApiService,
    private val myPrefs: MyPrefs
): ViewModel(){

    private var _foodBeveragesListStateFlowCustom = MutableStateFlow<List<FoodBeverageCustom>>(emptyList())
    val foodBeveragesListStateFlow = _foodBeveragesListStateFlowCustom.asStateFlow()

    private var _userMessageSharedFlow = MutableSharedFlow<Int>()
    val userMessageSharedFlow = _userMessageSharedFlow.asSharedFlow()

    private var _recipeCreatedSharedFlow = MutableSharedFlow<Boolean>()
    val recipeCreatedSharedFlow = _recipeCreatedSharedFlow.asSharedFlow()

    private var _progressBarLoadingStateFlow = MutableStateFlow(false)
    val progressBarLoadingStateFlow = _progressBarLoadingStateFlow.asStateFlow()


    private val listOfFoodBeverageDto = mutableListOf<FoodBeverageDto>()
    private val listOfUnits = mutableListOf<UnitDto>()
    private val listOfQuantity = mutableListOf<Int>()

    fun addIngredient(name: String, kcal: String, quantity: String, unit: String){
        val trimName = name.trim()
        val trimKcal = kcal.trim()
        val trimQuantity = quantity.trim()
        val trimUnit = unit.trim()
        if(
            trimName.isNotEmpty()
            && trimQuantity.isNotEmpty()
            && trimUnit.isNotEmpty()
            && trimKcal.isNotEmpty()
        ){
            _foodBeveragesListStateFlowCustom.value += FoodBeverageCustom(
                trimName,
                trimKcal.toInt(),
                trimQuantity.toInt(),
                trimUnit
            )
        } else
            viewModelScope.launch {
                _userMessageSharedFlow.emit(R.string.fill_fields)
            }
    }

    fun removeIngredient(name: String){
        _foodBeveragesListStateFlowCustom.value =
            _foodBeveragesListStateFlowCustom.value.filter { it.nameIngredient != name }
    }

    fun createRecipe(name: String, dishCategory: String){

        _foodBeveragesListStateFlowCustom.value.forEach {
            listOfFoodBeverageDto.add(FoodBeverageDto(it.nameIngredient, it.kcal))
            listOfUnits.add(UnitDto(it.unit))
            listOfQuantity.add(it.quantity)
        }

        viewModelScope.launch {
            _progressBarLoadingStateFlow.value = true

            try {
                val responseEdit = withContext(Dispatchers.IO){
                    apiService.createRecipe(myPrefs.token!!,
                        CreateRecipeDto(
                            name,
                            "publish",
                            dishCategory.lowercase(),
                            listOfFoodBeverageDto,
                            listOfQuantity,
                            listOfUnits,
                            myPrefs.userId
                        ) )
                }
                _progressBarLoadingStateFlow.value = false

                val body = responseEdit?.body()

                 when{
                    responseEdit == null ->
                        R.string.no_response_database

                    responseEdit.isSuccessful && body != null -> {
                        _recipeCreatedSharedFlow.emit(true)
                        R.string.recipe_created
                    }

                    responseEdit.code() == 400 ->
                        R.string.recipe_not_created

                    else -> return@launch
                }.let {
                     _userMessageSharedFlow.emit(it)
                 }
            } catch (ce: ConnectException){
                _progressBarLoadingStateFlow.value = false
                _userMessageSharedFlow.emit(R.string.no_response_database)
            }
        }
    }
}