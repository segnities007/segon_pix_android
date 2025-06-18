package com.example.feature_core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.segon_pix_android.feature_auth.Auth
import com.example.segon_pix_android.presentation.feature_hub.Hub
import com.example.segon_pix_android.presentation.model.FeatureRoute

@Composable
fun Core() {
    val navController = rememberNavController()

    NavHost(startDestination = FeatureRoute.Auth, navController = navController) {
        composable<FeatureRoute.Auth> {
            Auth(coreNavHostController = navController)
        }
        composable<FeatureRoute.Hub> {
            Hub()
        }
    }
}
