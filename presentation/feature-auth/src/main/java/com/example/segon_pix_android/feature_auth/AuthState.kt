package com.example.segon_pix_android.feature_auth

data class AuthState(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val description: String = "",
    val code: String = "",
    val birthday: Int = 20200101, // yyyyMMdd
)
