package com.example.kcalcul_compose.ui.shared_component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.kcalcul_compose.ui.navigation.BottomBarScreen
import com.example.kcalcul_compose.ui.navigation.Screen

@Preview(showBackground = true)
@Composable
fun BottomBarSharedComponentPreview(){
    //BottomBarSharedComponentContent()
}

@Composable
fun BottomBarSharedComponentContent(
    navController: NavController
){
    var  selectedScreen by rememberSaveable {
        mutableIntStateOf(0)
    }

    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Meal,
        BottomBarScreen.Calendar,
        BottomBarScreen.Settings
    )

    NavigationBar {
        screens.forEachIndexed { i, screen ->
            NavigationBarItem(
                selected = selectedScreen == i ,
                onClick = {
                    selectedScreen = i
                    navController.navigate(screen.route){
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.iconDesc
                    )
                },
                label = { Text(text = screen.title)}
            )
        }
    }
}

