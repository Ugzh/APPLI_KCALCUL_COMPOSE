package com.example.kcalcul_compose.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Flatware
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kcalcul_compose.ui.screens.splash.SplashScreen
import com.example.kcalcul_compose.ui.screens.edit.EditAccountScreen
import com.example.kcalcul_compose.ui.screens.edit.EditAccountViewModel
import com.example.kcalcul_compose.ui.screens.login.LoginViewModel
import com.example.kcalcul_compose.ui.screens.login.SignInScreen
import com.example.kcalcul_compose.ui.screens.recipe.CreateRecipeContent
import com.example.kcalcul_compose.ui.screens.recipe.CreateRecipeScreen
import com.example.kcalcul_compose.ui.screens.recipe.CreateRecipeViewModel
import com.example.kcalcul_compose.ui.screens.register.RegisterScreen
import com.example.kcalcul_compose.ui.screens.register.RegisterViewModel
import com.example.kcalcul_compose.ui.screens.splash.SplashViewModel
import com.example.kcalcul_compose.ui.shared_component.BottomBarSharedComponentContent


sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Edit : Screen("edit")
    data object CreateMeal : Screen("create_meal")
    data object Splash : Screen("splash")
}

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val iconDesc : String
) {
    data object Home: BottomBarScreen(
        route = Screen.Home.route,
        title = "Home",
        icon = Icons.Default.Home,
        iconDesc = "Home page"
    )
    data object Meal: BottomBarScreen(
        route = Screen.CreateMeal.route,
        title = "Meals",
        icon = Icons.Default.Flatware,
        iconDesc = "Eat page"
    )
    data object Calendar: BottomBarScreen(
        route = "Calendar",
        title = "Calendar",
        icon = Icons.Default.EditCalendar,
        iconDesc = "Calendar page"
    )
    data object Settings: BottomBarScreen(
        route = Screen.Edit.route,
        title = "Settings",
        icon = Icons.Default.Settings,
        iconDesc = "Settings page"
    )
}

@Composable
fun AppNavHost() {
    val navController =  rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val bottomBarRoutes = listOf(
        BottomBarScreen.Home.route,
        BottomBarScreen.Meal.route,
        BottomBarScreen.Calendar.route,
        BottomBarScreen.Settings.route
    )
    val showBottomBar =  navBackStackEntry?.destination?.route in bottomBarRoutes
    Scaffold(
        bottomBar ={
            if(showBottomBar)
                BottomBarSharedComponentContent(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                val splashViewModel: SplashViewModel = hiltViewModel()
                SplashScreen(navController, splashViewModel)
            }
            composable(Screen.Login.route) {
                val loginViewModel: LoginViewModel = hiltViewModel()
                SignInScreen(navController, loginViewModel)
            }
            composable(Screen.Register.route) {
                val registerViewModel: RegisterViewModel = hiltViewModel()
                RegisterScreen(navController, registerViewModel)
            }
            composable(Screen.Edit.route) {
                val editAccountViewModel: EditAccountViewModel = hiltViewModel()
                EditAccountScreen(navController, editAccountViewModel)
            }
            composable(Screen.CreateMeal.route) {
                val createRecipeViewModel: CreateRecipeViewModel = hiltViewModel()
                CreateRecipeScreen(navController, createRecipeViewModel)
            }
        }
    }
}