package com.example.segon_pix_android.presentation.feature_hub.profile

import androidx.compose.runtime.Immutable
import com.example.segon_pix_android.domain.model.User

@Immutable
data class ProfileState(
    val self: User = User()
)