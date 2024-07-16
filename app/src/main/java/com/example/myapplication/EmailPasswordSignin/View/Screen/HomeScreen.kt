package com.example.myapplication.EmailPasswordSignin.View.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.EmailPasswordSignin.Model.AuthState
import com.example.myapplication.EmailPasswordSignin.View.Navigation.Routes
import com.example.myapplication.EmailPasswordSignin.ViewModel.AuthViewModel

@Composable
fun HomeScreen(navController: NavController,authViewModel: AuthViewModel){
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate(Routes.LoginScreen)

            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Home Page ", fontSize = 32.sp)

        TextButton(onClick = {
            authViewModel.signout()
        }) {
            Text(text = "sign out")
        }

    }
}