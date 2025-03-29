package com.example.segon_pix_android.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationRoute(
    val label: String,
) {
    @Serializable data object Splash : NavigationRoute(label = "Splash")

    @Serializable data object Login : NavigationRoute(label = "Login")

    @Serializable data object Hub : NavigationRoute(label = "Hub")
}
