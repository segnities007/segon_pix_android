package com.example.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthTokenResponse(
    @SerialName("token") val token: String,
    @SerialName("user_id") val userId: Long? = null,
    @SerialName("email") val email: String? = null,
)
