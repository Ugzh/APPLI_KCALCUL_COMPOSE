package com.example.kcalcul_compose.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kcalcul_compose.ui.navigation.NavigationItem
import dagger.hilt.android.lifecycle.HiltViewModel

@Preview(showBackground = true)
@Composable
fun SplashPreview(){
    SplashContent()
}
@Composable
fun SplashContent(){
    Column(
        modifier = Modifier
            .background(Color.Cyan)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Kcalcul", modifier = Modifier)
    }
}
@Composable
fun SplashScreen(navController: NavController){
    val vm: SplashViewModel = hiltViewModel()
    LaunchedEffect(key1 = true) {
        vm.userAlreadyLogSharedFlow.collect{
            if(it)
                navController.navigate(NavigationItem.Edit.route)
            else
                navController.navigate(NavigationItem.Login.route)
        }
    }

    SplashContent()
}