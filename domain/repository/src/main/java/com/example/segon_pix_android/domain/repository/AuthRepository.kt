package com.example.segon_pix_android.domain.repository

import com.example.segon_pix_android.domain.model.AuthResult
import com.example.segon_pix_android.domain.model.MyCustomClaims
import com.example.segon_pix_android.domain.model.User

interface AuthRepository {
    fun getToken(): String?

    suspend fun getAuthenticatedUserClaims(token: String): MyCustomClaims?

    suspend fun isTokenVerified(): Boolean

    suspend fun sendEmailVerificationCode(email: String): Boolean

    suspend fun verifyAndAddUser(
        name: String,
        email: String,
        password: String,
        birthday: Int,
        code: String,
        description: String? = null,
    ): AuthResult

    suspend fun login(
        email: String,
        password: String,
    ): AuthResult

    suspend fun getUserById(userId: Long): User?

    suspend fun isTokenExpired(claims: MyCustomClaims?): Boolean
}
