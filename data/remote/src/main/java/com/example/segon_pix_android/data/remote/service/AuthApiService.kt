package com.example.segon_pix_android.data.remote.service

import com.example.dto.AuthTokenResponse
import com.example.dto.SendEmailCodeResponse
import com.example.dto.VerifiedAddUserRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {
    @POST("sendEmailverifiedCode")
    suspend fun sendEmailVerificationCode(
        @Query("email") email: String,
    ): SendEmailCodeResponse

    @POST("verifiedAddUser")
    suspend fun verifyAndAddUser(
        @Query("code") code: String,
        @Body request: VerifiedAddUserRequest,
    ): AuthTokenResponse

    @POST("login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String,
    ): AuthTokenResponse

    @GET("segon_pix_auth")
    suspend fun getRestrictedData(
        @Header("Authorization") token: String,
    ): String
}
