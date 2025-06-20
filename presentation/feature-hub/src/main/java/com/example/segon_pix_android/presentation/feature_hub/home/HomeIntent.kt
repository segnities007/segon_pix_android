package com.example.segon_pix_android.presentation.feature_hub.home

sealed interface HomeIntent {
    data object Init : HomeIntent

    data class GetMoreNewImage(
        val last: Int,
    ) : HomeIntent
}
