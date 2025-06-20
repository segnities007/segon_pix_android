package com.example.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    @SerialName("userID") val userId: Long, // GoのクエリパラメータをJSONボディとして扱う場合を想定
    @SerialName("name") val name: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("birthday") val birthday: Int? = null,
    @SerialName("email") val email: String? = null,
)
