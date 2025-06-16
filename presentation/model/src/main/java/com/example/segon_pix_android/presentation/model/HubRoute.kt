package com.example.segon_pix_android.presentation.model

sealed interface HubRoute {
    data object Home : HubRoute

    data object Search : HubRoute

    data object Chat : HubRoute

    data object Profile : HubRoute
}
