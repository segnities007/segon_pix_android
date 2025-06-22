package com.example.segon_pix_android.presentation.feature_hub.search

sealed interface SearchIntent {
    data object Init : SearchIntent

    data class Search(
        val query: String,
    ) : SearchIntent

    data object GetMoreImages : SearchIntent

    data class SetQuery(
        val query: String,
    ) : SearchIntent
}
