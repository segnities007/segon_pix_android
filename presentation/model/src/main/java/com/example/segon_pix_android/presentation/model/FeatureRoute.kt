package com.example.segon_pix_android.presentation.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface FeatureRoute {
    @Serializable
    data object Auth : FeatureRoute

    @Serializable
    data object Hub : FeatureRoute
}
