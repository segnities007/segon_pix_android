package com.example.feature_core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.segon_pix_android.feature_auth.Auth
import com.example.segon_pix_android.presentation.feature_hub.Hub
import com.example.segon_pix_android.presentation.model.CoreRoute

@Composable
fun Core() {
    val navController = rememberNavController()
    val coreViewModel: CoreViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        coreViewModel.onIntent(CoreIntent.Init(coreNavHostController = navController))
    }

    NavHost(startDestination = CoreRoute.Splash, navController = navController) {
        composable<CoreRoute.Splash> {}
        composable<CoreRoute.Auth> {
            Auth(coreNavHostController = navController)
        }
        composable<CoreRoute.Hub> {
            Hub(coreNavController = navController)
        }
    }
}
