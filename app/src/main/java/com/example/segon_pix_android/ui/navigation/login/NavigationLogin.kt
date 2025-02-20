package com.example.segon_pix_android.ui.navigation.login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.segon_pix_android.ui.screen.login.Login
import com.example.segon_pix_android.ui.screen.login.SignUpIn

@Composable
fun NavigationLogin() {
    val navController = rememberNavController()

    Login{
        NavHost(
            navController = navController,
            startDestination = NavigationLoginRoute.SignUpIn,
        ) {
            composable<NavigationLoginRoute.SignUpIn> { SignUpIn() }
        }
    }
}
