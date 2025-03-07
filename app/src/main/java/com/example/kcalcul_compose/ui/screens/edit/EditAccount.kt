package com.example.kcalcul_compose.ui.screens.edit

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.ui.shared_component.ButtonSharedComponent
import com.example.kcalcul_compose.ui.shared_component.EditTextSharedComponent
import com.example.kcalcul_compose.ui.shared_component.TitleSharedComponent
import com.example.kcalcul_compose.utils.User
import kotlinx.coroutines.flow.collect

@Preview(showBackground = true)
@Composable
fun EditAccountPreview(){
    EditAccountContent(
        User(
            "Ugo",
            "Thevenin",
            "ugo@gmail.com",
            "x",
            1000),
        updateUser = {s, s2, s3, s4 ->  },
        backNavigation = {},
        true
    )
}

@Composable
fun EditAccountContent(
    user: User,
    updateUser: (String, String, String, String) -> Unit,
    backNavigation: () -> Unit,
    isLoading: Boolean
    ){
    val context = LocalContext.current
    Log.d("test2",user.firstname + " recompo")

    var firstname by remember {
        Log.d("test2",user.firstname)
        mutableStateOf(user.firstname)
    }
    var lastname by remember {
        mutableStateOf(user.lastname)
    }
    var email by remember {
        mutableStateOf(user.email)
    }
    var dailyRequirements by remember {
        mutableStateOf(user.dailyRequirements.toString())
    }
    Box(Modifier.fillMaxSize()) {
        TitleSharedComponent(
            text = context.getString(R.string.edit_account),
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Column(
            Modifier
                .align(Alignment.Center)
                .padding(horizontal = 60.dp)) {
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.firstname),
                textToEdit = firstname,
                onChangeText = {
                    firstname = it
                }
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.lastname),
                textToEdit = lastname,
                onChangeText = {
                    lastname = it
                }
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.email),
                textToEdit = email,
                onChangeText = {
                    email = it
                }
            )
            EditTextSharedComponent(
                placeholderStr = context.getString(R.string.daily_requirements),
                textToEdit = dailyRequirements,
                keyboardType = KeyboardType.Number,
                onChangeText = {
                    dailyRequirements = it
                }
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth()
            ) {
                ButtonSharedComponent(
                    btnText = context.getString(R.string.back),
                    modifier = Modifier,
                    onClickAction = {
                        backNavigation()

                    }
                )
                ButtonSharedComponent(
                    btnText = context.getString(R.string.validate),
                    modifier = Modifier,
                    onClickAction = {
                        updateUser(firstname, lastname, email, dailyRequirements)
                    }
                )
            }
            Column(Modifier.align(Alignment.CenterHorizontally)) {
                if(isLoading)
                    CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun EditAccountScreen(navController: NavController, vm: EditAccountViewModel){
    val context = LocalContext.current
    val isLoading by vm.progressBarLoadingStateFlow.collectAsState()

    val user by vm.userStateFlow.collectAsState()

    LaunchedEffect(key1 = true) {
        vm.updatedUserSharedFlow.collect{
            if(it)
                navController.popBackStack()
        }
    }

    LaunchedEffect(key1 = true) {
        vm.userMessageSharedFlow.collect{
            Toast.makeText(context, context.getString(it), Toast.LENGTH_SHORT).show()
        }
    }

    user?.let {
        EditAccountContent(
            it,
            updateUser = { firstname, lastname, email, dailyRequirements ->
                vm.updateUser(
                    firstname,
                    lastname,
                    email,
                    dailyRequirements.toInt()
                )
            },
            backNavigation = { navController.popBackStack() },
            isLoading
        )
    }
}