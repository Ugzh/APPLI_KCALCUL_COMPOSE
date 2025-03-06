package com.example.kcalcul_compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kcalcul_compose.ui.screens.splash.SplashScreen
import com.example.kcalcul_compose.ui.screens.edit.EditAccountScreen
import com.example.kcalcul_compose.ui.screens.login.SignInScreen
import com.example.kcalcul_compose.ui.screens.register.RegisterScreen

enum class Screens {
    HOME,
    LOGIN,
    REGISTER,
    EDIT,
    CREATE_MEAL,
    SPLASH,
}
sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(Screens.HOME.name)
    data object Login : NavigationItem(Screens.LOGIN.name)
    data object Register : NavigationItem(Screens.REGISTER.name)
    data object Edit : NavigationItem(Screens.EDIT.name)
    data object CreateMeal : NavigationItem(Screens.CREATE_MEAL.name)
    data object Splash : NavigationItem(Screens.SPLASH.name)

}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = NavigationItem.Splash.route
) {
    val navController =  rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Splash.route) {
            SplashScreen(navController)
        }
        composable(NavigationItem.Login.route) {
            SignInScreen()
        }
        composable(NavigationItem.Register.route) {
            RegisterScreen()
        }
        composable(NavigationItem.Edit.route) {
            EditAccountScreen()
        }
        composable(NavigationItem.CreateMeal.route) {
            EditAccountScreen()
        }
    }
}