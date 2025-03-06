package com.example.kcalcul_compose.ui.screens.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kcalcul_compose.network.ApiService
import com.example.kcalcul_compose.network.MyPrefs
import com.example.kcalcul_compose.network.dtos.foodsBeverages.CreateRecipeDto
import com.example.kcalcul_compose.network.dtos.foodsBeverages.FoodBeverageDto
import com.example.kcalcul_compose.network.dtos.foodsBeverages.UnitDto
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.utils.FoodBeverage
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

    private var _foodBeveragesListStateFlow = MutableStateFlow<List<FoodBeverage>>(emptyList())
    val foodBeveragesListStateFlow = _foodBeveragesListStateFlow.asStateFlow()

    private var _userMessageSharedFlow = MutableSharedFlow<Int>()
    val userMessageSharedFlow = _userMessageSharedFlow.asSharedFlow()

    private var _recipeCreatedSharedFlow = MutableSharedFlow<Boolean>()
    val recipeCreatedSharedFlow = _recipeCreatedSharedFlow.asSharedFlow()

    private var _progressBarLoadingSharedFlow = MutableSharedFlow<Boolean>()
    val progressBarLoadingSharedFlow = _progressBarLoadingSharedFlow.asSharedFlow()


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
            _foodBeveragesListStateFlow.value += FoodBeverage(
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
        _foodBeveragesListStateFlow.value =
            _foodBeveragesListStateFlow.value.filter { it.nameIngredient != name }
    }

    fun createRecipe(){
        _foodBeveragesListStateFlow.value.forEach {
            listOfFoodBeverageDto.add(FoodBeverageDto(it.nameIngredient, it.kcal))
            listOfUnits.add(UnitDto(it.unit))
            listOfQuantity.add(it.quantity)
        }
        viewModelScope.launch {
            _progressBarLoadingSharedFlow.emit(true)

            try {
                val responseEdit = withContext(Dispatchers.IO){
                    apiService.createRecipe(myPrefs.token!!,
                        CreateRecipeDto(
                            "tests5",
                            "publish",
                            "main course",
                            listOfFoodBeverageDto,
                            listOfQuantity,
                            listOfUnits,
                            myPrefs.userId
                        ) )
                }
                _progressBarLoadingSharedFlow.emit(false)

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
                _progressBarLoadingSharedFlow.emit(false)
                _userMessageSharedFlow.emit(R.string.no_response_database)
            }
        }
    }
}