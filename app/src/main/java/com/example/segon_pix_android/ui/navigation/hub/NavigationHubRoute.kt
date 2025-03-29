package com.example.segon_pix_android.ui.navigation.hub

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationHubRoute(
    val label: String,
) {
    @Serializable data object Home : NavigationHubRoute(label = "Home")

    @Serializable data object Trend : NavigationHubRoute(label = "Trend")

    @Serializable data object Post : NavigationHubRoute(label = "Post")

    @Serializable data object Notify : NavigationHubRoute(label = "Notify")

    @Serializable data object Setting : NavigationHubRoute(label = "Setting")
}
