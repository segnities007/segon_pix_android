package com.example.feature_core

import androidx.navigation.NavHostController

sealed interface CoreIntent {
    data class Init(
        val coreNavHostController: NavHostController,
    ) : CoreIntent
}
