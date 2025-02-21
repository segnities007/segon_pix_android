package com.example.segon_pix_android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.segon_pix_android.ui.navigation.hub.NavigationHub
import com.example.segon_pix_android.ui.navigation.login.NavigationLogin
import com.example.segon_pix_android.ui.screen.hub.Hub
import com.example.segon_pix_android.ui.screen.login.Login
import com.example.segon_pix_android.ui.screen.splash.Splash

@Composable
fun Navigation() {
    val navTopController = rememberNavController()

    NavHost(
        navController = navTopController,
        startDestination = NavigationRoute.Splash,
    ) {
        composable<NavigationRoute.Splash> { Splash(navTopController) }
        composable<NavigationRoute.Login> { NavigationLogin(navTopController) }
        composable<NavigationRoute.Hub> { NavigationHub(navTopController) }
    }
}
