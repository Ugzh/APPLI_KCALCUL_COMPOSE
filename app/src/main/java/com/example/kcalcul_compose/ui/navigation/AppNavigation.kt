package com.example.kcalcul_compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kcalcul_compose.ui.screens.splash.SplashScreen
import com.example.kcalcul_compose.ui.screens.edit.EditAccountScreen
import com.example.kcalcul_compose.ui.screens.login.LoginViewModel
import com.example.kcalcul_compose.ui.screens.login.SignInScreen
import com.example.kcalcul_compose.ui.screens.register.RegisterScreen
import com.example.kcalcul_compose.ui.screens.register.RegisterViewModel
import com.example.kcalcul_compose.ui.screens.splash.SplashViewModel


sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Edit : Screen("edit")
    data object CreateMeal : Screen("create_meal")
    data object Splash : Screen("splash")
}

@Composable
fun AppNavHost() {
    val navController =  rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Register.route
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
            EditAccountScreen()
        }
        composable(Screen.CreateMeal.route) {
            EditAccountScreen()
        }
    }
}