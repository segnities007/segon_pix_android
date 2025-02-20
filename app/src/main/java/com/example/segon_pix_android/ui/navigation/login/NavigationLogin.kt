package com.example.segon_pix_android.ui.navigation.login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.segon_pix_android.ui.screen.login.Login

@Composable
fun NavigationLogin() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationLoginRoute.Login,
    ) {
        composable<NavigationLoginRoute.Login> { Login() }
    }
}
