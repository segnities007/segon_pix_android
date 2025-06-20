package com.example.segon_pix_android.presentation.feature_hub.profile

import androidx.navigation.NavHostController
import com.example.segon_pix_android.domain.model.User

sealed interface ProfileIntent {
    data object Init : ProfileIntent

    data class SignOut(
        val coreNavController: NavHostController,
    ) : ProfileIntent

    data class DeleteAccount(
        val coreNavController: NavHostController,
    ) : ProfileIntent

    data class UpdateProfile(
        val updatedUser: User,
    ) : ProfileIntent
}
