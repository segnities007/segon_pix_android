package com.example.segon_pix_android.ui.navigation.login

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationLoginRoute(
    val label: String,
) {
    @Serializable data object SignUpIn : NavigationLoginRoute(label = "SignUpIn")
}
