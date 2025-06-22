package com.example.segon_pix_android.data.repoitory_impl

import android.util.Log
import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT
import com.example.dto.VerifiedAddUserRequest
import com.example.dto.toDomainUser
import com.example.segon_pix_android.data.remote.AuthTokenManager
import com.example.segon_pix_android.data.remote.service.AuthApiService
import com.example.segon_pix_android.data.remote.service.UserApiService
import com.example.segon_pix_android.domain.model.AuthResult
import com.example.segon_pix_android.domain.model.MyCustomClaims
import com.example.segon_pix_android.domain.model.User
import com.example.segon_pix_android.domain.repository.AuthRepository
import jakarta.inject.Inject
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

class AuthRepositoryImpl
    @Inject
    constructor(
        private val authApiService: AuthApiService,
        private val userApiService: UserApiService,
        private val tokenManager: AuthTokenManager,
    ) : AuthRepository {
        override fun getToken(): String? = tokenManager.getToken()

        override suspend fun isTokenVerified(): Boolean {
            val token = tokenManager.getToken() ?: return false
            val claims = getAuthenticatedUserClaims(token) ?: return false
            return !isTokenExpired(claims)
        }

        override suspend fun getSelfId(): Long {
            val claims = getAuthenticatedUserClaims(getToken()!!)
            return claims!!.userId
        }

        override suspend fun sendEmailVerificationCode(email: String): Boolean =
            try {
                val response = authApiService.sendEmailVerificationCode(email)
                response.code() == 200
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }

        override suspend fun verifyAndAddUser(
            name: String,
            email: String,
            password: String,
            birthday: Int,
            code: String,
            description: String?,
        ): AuthResult =
            try {
                val request = VerifiedAddUserRequest(name, description, email, password, birthday)
                val response = authApiService.verifyAndAddUser(code, request)
                response.token.let { token ->
                    tokenManager.saveToken(token)
                }
                AuthResult(
                    token = response.token,
                    userId = response.userId,
                    email = response.email,
                    isSuccess = true,
                )
            } catch (e: Exception) {
                e.printStackTrace()
                AuthResult(errorMessage = e.message, isSuccess = false)
            }

        override suspend fun login(
            email: String,
            password: String,
        ): AuthResult =
            try {
                val response = authApiService.login(email, password)
                response.token.let { token ->
                    tokenManager.saveToken(token)
                }
                AuthResult(
                    token = response.token,
                    userId = response.userId,
                    email = response.email,
                    isSuccess = true,
                )
            } catch (e: Exception) {
                e.printStackTrace()
                AuthResult(errorMessage = e.message, isSuccess = false)
            }

        override suspend fun logout() {
            tokenManager.clearToken()
        }

        override suspend fun getAuthenticatedUserClaims(token: String): MyCustomClaims? {
            val decodedJwt: DecodedJWT? =
                try {
                    JWT.decode(token)
                } catch (e: Exception) {
                    e.printStackTrace()
                    return null
                }

            val claims =
                decodedJwt?.let { jwt ->
                    Log.d("AuthRepositoryImpl", "getAuthenticatedUserClaims: ${jwt.claims} ${jwt.header}")
                    MyCustomClaims(
                        email = jwt.getClaim("email").asString(),
                        userId = jwt.getClaim("userid").asLong(),
                        issuer = jwt.issuer,
                        subject = jwt.subject,
                        audience = jwt.audience,
                        expiration = jwt.expiresAt?.time?.div(1000L),
                        notBefore = jwt.notBefore?.time?.div(1000L),
                        issuedAt = jwt.issuedAt?.time?.div(1000L),
                        jwtId = jwt.id,
                    )
                }

            if (isTokenExpired(claims)) {
                tokenManager.clearToken()
                return null
            }

            return claims
        }

        override suspend fun getUserById(userId: Long): User? =
            try {
                val response = userApiService.getUserPublic(userId)
                response.toDomainUser()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }

        @OptIn(ExperimentalTime::class)
        override suspend fun isTokenExpired(claims: MyCustomClaims?): Boolean {
            var isExpired = false
            claims?.expiration?.let { exp ->
                isExpired = (Instant.fromEpochSeconds(exp) <= Clock.System.now())
            }
            if (isExpired) tokenManager.clearToken()
            return isExpired
        }
    }
