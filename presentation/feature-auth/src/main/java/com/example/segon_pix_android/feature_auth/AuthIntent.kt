package com.example.segon_pix_android.feature_auth

import androidx.compose.foundation.pager.PagerState
import androidx.navigation.NavHostController
import com.example.segon_pix_android.presentation.model.FeatureRoute

sealed interface AuthIntent {
    data class GoToNextPage(
        val pageIndex: Int,
        val pagerState: PagerState,
    ) : AuthIntent

    data class GoToHub(
        val featureRoute: FeatureRoute,
        val coreNavHostController: NavHostController,
    ) : AuthIntent

    data class SignIn(
        val email: String,
        val password: String,
        val coreNavHostController: NavHostController,
    ) : AuthIntent

    data class SetEmailAndPassword(
        val email: String,
        val password: String,
        val pagerState: PagerState,
    ) : AuthIntent

    data class CreateAccount(
        val name: String,
        val code: String,
        val birthday: String,
        val coreNavHostController: NavHostController,
    ) : AuthIntent

    data class BackToStart(
        val pagerState: PagerState,
    ) : AuthIntent

    data class Init(
        val coreNavHostController: NavHostController,
    ) : AuthIntent
}
