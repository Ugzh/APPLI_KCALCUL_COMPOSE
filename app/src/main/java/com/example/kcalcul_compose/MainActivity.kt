package com.example.kcalcul_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.kcalcul_compose.ui.screens.edit.EditAccount
import com.example.kcalcul_compose.ui.screens.edit.EditAccountPreview
import com.example.kcalcul_compose.ui.screens.register.Register
import com.example.kcalcul_compose.ui.theme.KCALCUL_COMPOSETheme

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
                    //Register()
                    //EditAccountPreview()
                }
            }
        }
    }
}
