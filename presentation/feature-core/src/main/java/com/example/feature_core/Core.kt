package com.example.feature_core

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.segon_pix_android.feature_auth.Auth
import com.example.segon_pix_android.presentation.feature_hub.Hub
import com.example.segon_pix_android.presentation.model.FeatureRoute

@Composable
fun Core() {
    val navController = rememberNavController()
    val coreViewModel: CoreViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        coreViewModel.onIntent(CoreIntent.Init(coreNavHostController = navController))
    }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            Log.d("Core", "Core: ${backStackEntry.destination.route}")
        }
    }

    NavHost(startDestination = FeatureRoute.Splash, navController = navController) {
        composable<FeatureRoute.Splash> {
        }
        composable<FeatureRoute.Auth> {
            Auth(coreNavHostController = navController)
        }
        composable<FeatureRoute.Hub> {
            Hub()
        }
    }
}
