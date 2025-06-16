package com.example.segon_pix_android.domain.repository

import com.example.segon_pix_android.domain.model.AuthResult
import com.example.segon_pix_android.domain.model.MyCustomClaims
import com.example.segon_pix_android.domain.model.User
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

interface AuthRepository {
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

    suspend fun getAuthenticatedUserClaims(token: String): MyCustomClaims?

    suspend fun getUserById(userId: Long): User?

    @OptIn(ExperimentalTime::class)
    fun isTokenExpired(claims: MyCustomClaims?): Boolean =
        claims?.expiration?.let { exp ->
            val expirationInstant = Instant.fromEpochSeconds(exp)
            expirationInstant <= Clock.System.now()
        } != false
}
