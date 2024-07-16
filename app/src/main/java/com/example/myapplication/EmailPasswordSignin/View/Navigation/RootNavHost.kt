package com.example.myapplication.EmailPasswordSignin.View.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.EmailPasswordSignin.View.Screen.HomeScreen
import com.example.myapplication.EmailPasswordSignin.View.Screen.LoginScreen
import com.example.myapplication.EmailPasswordSignin.View.Screen.SignUpScreen
import com.example.myapplication.EmailPasswordSignin.ViewModel.AuthViewModel

@Composable
fun RootNavHost(authViewModel: AuthViewModel){
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Routes.LoginScreen, builder = {
        composable(Routes.LoginScreen){
            LoginScreen( navController = navController, authViewModel = authViewModel)
        }

        composable(Routes.SignUpScreen){
            SignUpScreen( navController = navController, authViewModel = authViewModel)
        }

        composable(Routes.HomeScreen){
            HomeScreen(navController = navController, authViewModel = authViewModel)

        }
    })
}