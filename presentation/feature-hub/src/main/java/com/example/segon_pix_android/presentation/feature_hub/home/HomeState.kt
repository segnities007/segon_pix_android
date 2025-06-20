package com.example.segon_pix_android.presentation.feature_hub.home

import androidx.compose.runtime.Immutable
import com.example.segon_pix_android.domain.model.Image

@Immutable
data class HomeState(
    val newImages: List<Image> = listOf(),
)
