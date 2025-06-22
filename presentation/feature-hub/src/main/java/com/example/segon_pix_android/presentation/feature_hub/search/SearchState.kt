package com.example.segon_pix_android.presentation.feature_hub.search

import com.example.segon_pix_android.domain.model.Image

data class SearchState(
    val images: List<Image> = emptyList(),
    val isFetchCompleted: Boolean = false,
    val query: String = "",
)
