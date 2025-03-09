package com.example.kcalcul_compose.ui.screens.createMeal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.network.ApiService
import com.example.kcalcul_compose.network.MyPrefs
import com.example.kcalcul_compose.network.dtos.foodsBeverages.FoodBeverageResponseDto
import com.example.kcalcul_compose.network.dtos.foodsBeverages.UnitDto
import com.example.kcalcul_compose.network.dtos.recipes.RecipeDto
import com.example.kcalcul_compose.utils.RecipeCustom
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
class CreateMealViewModel @Inject constructor(
    private val apiService: ApiService,
    private val myPrefs: MyPrefs
): ViewModel(){

    /*
    private var _mealCreatedSharedFlow = MutableSharedFlow<Boolean>()
    val mealCreatedSharedFlow = _mealCreatedSharedFlow.asSharedFlow()

    private var _progressBarLoadingStateFlow = MutableStateFlow(false)
    val progressBarLoadingStateFlow = _progressBarLoadingStateFlow.asStateFlow()*/


    private var _foodsBeveragesStateFlow =
        MutableStateFlow<List<FoodBeverageResponseDto>>(emptyList())
    val foodsBeveragesStateFlow = _foodsBeveragesStateFlow.asStateFlow()

    private var _recipesStateFlow =
        MutableStateFlow<List<RecipeDto>>(emptyList())
    val recipesStateFlow = _recipesStateFlow.asStateFlow()

    private var _userMessageSharedFlow = MutableSharedFlow<Int>()
    val userMessageSharedFlow = _userMessageSharedFlow.asSharedFlow()

    private var _recipeSelectedByUser = MutableStateFlow<List<RecipeCustom>>(emptyList())
    val recipeSelectedByUser = _recipeSelectedByUser.asStateFlow()


    private val listOfQuantitiesForRecipes = mutableListOf<Int>()
    private val listOfQuantitiesForFoodBeverage = mutableListOf<Int>()
    private val listOfUnits = mutableListOf<UnitDto>()
    init{
        getAllFoodsBeverages()
        getAllRecipes()
    }
    private fun getAllFoodsBeverages(){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO){
                    apiService.getAllFoodBeverages(myPrefs.token!!)
                }

                val body = response?.body()

                when{
                    response == null ->
                        R.string.no_response_database

                    response.isSuccessful && body != null -> {
                        _foodsBeveragesStateFlow.value = body.foodBeverage
                    }

                    else -> return@launch
                }
            } catch (ce: ConnectException){
                _userMessageSharedFlow.emit(R.string.no_response_database)
            }
        }
    }

    private fun getAllRecipes(){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO){
                    apiService.getAllRecipes(myPrefs.token!!)
                }

                val body = response?.body()

                when{
                    response == null ->
                        R.string.no_response_database

                    response.isSuccessful && body != null -> {
                        _recipesStateFlow.value = body.recipes
                    }

                    else -> return@launch
                }
            } catch (ce: ConnectException){
                _userMessageSharedFlow.emit(R.string.no_response_database)
            }
        }
    }

    fun addRecipe(recipe: RecipeDto, qty: String){
        val trimQty = qty.trim()
        if(trimQty.isNotEmpty()){
            _recipeSelectedByUser.value += RecipeCustom(recipe, qty.toInt())
        } else
            viewModelScope.launch {
                _userMessageSharedFlow.emit(R.string.fill_fields)
            }
    }

    //When clicking on recipe delete all recipe with same name
    fun removeRecipe(recipe: RecipeDto){
        _recipeSelectedByUser.value =
            _recipeSelectedByUser.value.filter { it.recipe != recipe }
    }
    /*fun createRecipe(name: String, dishCategory: String){

        _foodBeveragesListStateFlow.value.forEach {
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
    }*/
}