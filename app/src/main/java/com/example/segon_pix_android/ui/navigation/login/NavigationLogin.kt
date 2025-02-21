package com.example.segon_pix_android.ui.navigation.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.segon_pix_android.ui.screen.login.Login
import com.example.segon_pix_android.ui.screen.login.SignUpIn

@Composable
fun NavigationLogin(
    navTopController: NavHostController,
) {
    val navLoginController = rememberNavController()

    Login{
        NavHost(
            navController = navLoginController,
            startDestination = NavigationLoginRoute.SignUpIn,
        ) {
            composable<NavigationLoginRoute.SignUpIn> { SignUpIn() }
        }
    }
}

@Composable
@Preview
private fun NavigationLoginPreview(){
    val navController = rememberNavController()
    NavigationLogin(navController)
}