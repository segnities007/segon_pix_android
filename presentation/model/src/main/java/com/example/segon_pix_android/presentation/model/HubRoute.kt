package com.example.segon_pix_android.presentation.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface HubRoute {
    @Serializable
    data object Home : HubRoute

    @Serializable
    data object Search : HubRoute

    @Serializable
    data object Chat : HubRoute

    @Serializable
    data object Profile : HubRoute
}
