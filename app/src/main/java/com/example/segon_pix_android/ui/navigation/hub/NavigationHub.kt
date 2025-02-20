package com.example.segon_pix_android.ui.navigation.hub

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.segon_pix_android.ui.screen.hub.home.Home
import com.example.segon_pix_android.ui.screen.hub.post.Post
import com.example.segon_pix_android.ui.screen.hub.setting.Setting
import com.example.segon_pix_android.ui.screen.hub.trend.Trend

@Composable
fun NavigationHub() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationHubRoute.Home,
    ) {
        composable<NavigationHubRoute.Home> { Home() }
        composable<NavigationHubRoute.Trend> { Trend() }
        composable<NavigationHubRoute.Post> { Post() }
        composable<NavigationHubRoute.Notify> { Post() }
        composable<NavigationHubRoute.Setting> { Setting() }
    }
}
