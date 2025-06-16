package com.example.segon_pix_android.presentation.feature_hub

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.segon_pix_android.presentation.feature_hub.chat.Chat
import com.example.segon_pix_android.presentation.feature_hub.home.Home
import com.example.segon_pix_android.presentation.feature_hub.profile.Profile
import com.example.segon_pix_android.presentation.feature_hub.search.Search
import com.example.segon_pix_android.presentation.model.HubRoute

@Composable
fun Hub() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HubRoute.Home) {
        composable<HubRoute.Home> {
            Home()
        }
        composable<HubRoute.Search> {
            Search()
        }
        composable<HubRoute.Chat> {
            Chat()
        }
        composable<HubRoute.Profile> {
            Profile()
        }
    }
}

@Composable
private fun HubUi(content: @Composable () -> Unit) {
    Scaffold { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            content()
        }
    }
}
