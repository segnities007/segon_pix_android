package com.example.segon_pix_android.ui.navigation.hub

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.segon_pix_android.ui.component.bar.bottom_bar.BottomNavigationBarViewModel
import com.example.segon_pix_android.ui.screen.hub.Hub
import com.example.segon_pix_android.ui.screen.hub.home.Home
import com.example.segon_pix_android.ui.screen.hub.post.Post
import com.example.segon_pix_android.ui.screen.hub.setting.Setting
import com.example.segon_pix_android.ui.screen.hub.trend.Trend
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun NavigationHub(
    navTopController: NavHostController,
) {
    val bottomNavigationBarViewModel: BottomNavigationBarViewModel = viewModel()
    val navHubController = rememberNavController()

    Hub(
        selectedIndex = bottomNavigationBarViewModel.index,
        bottomNavigationBarAction = bottomNavigationBarViewModel.onGetBottomNavigationBarAction(),
    ){
        NavHost(
            navController = navHubController,
            startDestination = NavigationHubRoute.Home,
        ) {
            composable<NavigationHubRoute.Home> { Home() }
            composable<NavigationHubRoute.Trend> { Trend() }
            composable<NavigationHubRoute.Post> { Post() }
            composable<NavigationHubRoute.Notify> { Post() }
            composable<NavigationHubRoute.Setting> { Setting() }
        }
    }
}
