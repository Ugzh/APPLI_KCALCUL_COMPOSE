package com.example.kcalcul_compose.ui.screens.createMeal

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.network.ApiService
import com.example.kcalcul_compose.network.MyPrefs
import com.example.kcalcul_compose.network.dtos.foodsBeverages.FoodBeverageDto
import com.example.kcalcul_compose.network.dtos.foodsBeverages.FoodBeverageResponseDto
import com.example.kcalcul_compose.network.dtos.foodsBeverages.UnitDto
import com.example.kcalcul_compose.network.dtos.meal.CreateMealDto
import com.example.kcalcul_compose.network.dtos.recipes.CreateRecipeDto
import com.example.kcalcul_compose.network.dtos.recipes.RecipeDto
import com.example.kcalcul_compose.utils.FoodBeverageCustom
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

    private var _mealCreatedSharedFlow = MutableSharedFlow<Boolean>()
    val mealCreatedSharedFlow = _mealCreatedSharedFlow.asSharedFlow()

    private var _progressBarLoadingStateFlow = MutableStateFlow(false)
    val progressBarLoadingStateFlow = _progressBarLoadingStateFlow.asStateFlow()


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

    private var _foodBeverageSelectedByUser = MutableStateFlow<List<FoodBeverageCustom>>(emptyList())
    val foodBeverageSelectedByUser = _foodBeverageSelectedByUser.asStateFlow()

    private val listOfFoodBeverages = mutableListOf<FoodBeverageDto>()
    private val listOfFoodBeveragesQty = mutableListOf<Int>()
    private val listOfRecipesQty = mutableListOf<Int>()
    private val listOfRecipes = mutableListOf<RecipeDto>()
    private val listOfUnit = mutableListOf<String>()

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
        }
        else
            viewModelScope.launch {
                _userMessageSharedFlow.emit(R.string.fill_fields)
            }
    }

    fun removeRecipe(recipe: RecipeDto){
        _recipeSelectedByUser.value =
            _recipeSelectedByUser.value.filter { it.recipe.id != recipe.id }
    }
    fun addFoodBeverage(foodBeverage: FoodBeverageResponseDto, qty: String, unit: String){
        val trimQty = qty.trim()
        val trimUnit = unit.trim()
        if(trimQty.isNotEmpty() && trimUnit.isNotEmpty()){
            _foodBeverageSelectedByUser.value +=
                FoodBeverageCustom(foodBeverage.name, foodBeverage.kcal, qty.toInt(), unit)
        }
        else
            viewModelScope.launch {
                _userMessageSharedFlow.emit(R.string.fill_fields)
            }
    }

    fun removeFoodBeverage(foodBeverage: FoodBeverageCustom){
        _foodBeverageSelectedByUser.value =
            _foodBeverageSelectedByUser.value.filter { it != foodBeverage }
    }

    fun createMeal(notification: Boolean, date: String, mealType: String){
        if(date.isNotEmpty() && mealType.isNotEmpty()){
            clearAllRequestLists()
            _recipeSelectedByUser.value.forEach {
                with(it.recipe){
                    listOfRecipes.add(RecipeDto(id, name, dish, state, createdAt, updatedAt))
                }
                    listOfRecipesQty.add(it.qty)
            }

            _foodBeverageSelectedByUser.value.forEach {
                listOfFoodBeverages.add(FoodBeverageDto(it.nameIngredient, it.kcal, it.unit))
                listOfFoodBeveragesQty.add(it.quantity)
                listOfUnit.add(it.unit)
            }

            viewModelScope.launch{
                _progressBarLoadingStateFlow.value = true

                try {
                    val responseEdit = withContext(Dispatchers.IO){
                        apiService.createMeal(
                            myPrefs.token!!,
                            CreateMealDto(
                                notification,
                                date,
                                listOfFoodBeverages,
                                listOfRecipes,
                                myPrefs.userId,
                                mealType.lowercase(),
                                listOfRecipesQty,
                                listOfFoodBeveragesQty,
                                listOfUnit
                            )
                        )
                    }
                    _progressBarLoadingStateFlow.value = false

                    val body = responseEdit?.body()

                    when{
                        responseEdit == null ->
                            R.string.no_response_database

                        responseEdit.isSuccessful && body != null -> {
                            _mealCreatedSharedFlow.emit(true)
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
        }else
            viewModelScope.launch {
                _userMessageSharedFlow.emit(R.string.fill_fields)
            }
    }

    private fun clearAllRequestLists(){
        listOfRecipes.clear()
        listOfFoodBeverages.clear()
        listOfFoodBeveragesQty.clear()
        listOfRecipesQty.clear()
        listOfUnit.clear()
    }
}