package com.example.myapplication.PhoneNoLogin.View

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.PhoneNoLogin.ViewModel.AuthViewModel

@Composable
fun NavGraph(navController: NavHostController, authViewModel: AuthViewModel) {
    NavHost(navController = navController, startDestination = "auth") {
        composable("auth") {
            AuthScreen(authViewModel, navController)
        }
        composable("home") {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // Your Home screen content goes here
    Text(text = "Welcome to Home Screen", modifier = modifier.fillMaxSize(), textAlign = TextAlign.Center)
}
