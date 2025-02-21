package com.example.segon_pix_android.ui.screen.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.example.segon_pix_android.ui.navigation.NavigationRoute

@Composable
fun Splash(
    navTopController: NavHostController
) {
    LaunchedEffect(Unit) {
        navTopController.navigate(NavigationRoute.Hub)
    }
    Text("s")
}
