package com.example.segon_pix_android.presentation.feature_hub.home

import android.net.Uri

sealed interface HomeIntent {
    data object Init : HomeIntent

    data class GetMoreNewImage(
        val last: Int,
    ) : HomeIntent

    data class PostImage(
        val uri: Uri,
        val title: String,
        val hashtag: String,
    ): HomeIntent
}
