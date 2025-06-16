package com.example.segon_pix_android.domain.model

data class AuthResult(
    val token: String? = null,
    val userId: Long? = null,
    val email: String? = null,
    val errorMessage: String? = null,
    val isSuccess: Boolean,
)
