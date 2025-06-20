package com.example.dto

import kotlinx.serialization.Serializable

@Serializable
data class SendEmailCodeResponse(
    val message: String,
)
