package com.example.kcalcul_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.kcalcul_compose.ui.navigation.AppNavHost
import com.example.kcalcul_compose.ui.screens.register.RegisterScreen
import com.example.kcalcul_compose.ui.shared_component.IngredientEditsTextsContent
import com.example.kcalcul_compose.ui.theme.KCALCUL_COMPOSETheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KCALCUL_COMPOSETheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    //RegisterScreen()
                    //EditAccountPreview()
                    AppNavHost()
                    //IngredientEditsTextsContent()
                }
            }
        }
    }
}
