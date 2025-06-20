package com.example.segon_pix_android.presentation.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface CoreRoute {
    @Serializable
    data object Auth : CoreRoute

    @Serializable
    data object Hub : CoreRoute

    @Serializable
    data object Splash : CoreRoute
}
