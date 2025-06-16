package com.example.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifiedAddUserRequest(
    @SerialName("name") val name: String,
    @SerialName("description") val description: String? = null,
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("birthday") val birthday: Int,
)
