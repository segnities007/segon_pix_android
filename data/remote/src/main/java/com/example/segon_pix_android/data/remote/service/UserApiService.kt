package com.example.segon_pix_android.data.remote.service

import com.example.dto.UserResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query

interface UserApiService {
    @GET("segon_pix/get/user")
    suspend fun getUserPublic(
        @Query("userID") userId: Long,
    ): UserResponse

    @GET("segon_pix_auth/get/user")
    suspend fun getUserAuthenticated(
        @Header("Authorization") token: String,
        @Query("userID") userId: Long,
        @Query("email") email: String,
        @Query("password") password: String,
    ): UserResponse

    @DELETE("segon_pix_auth/delete/user")
    suspend fun deleteUser(
        @Header("Authorization") token: String,
        @Query("userID") userId: Long,
    ): Response<Unit>

    @POST("segon_pix_auth/add/follow")
    suspend fun addFollow(
        @Header("Authorization") token: String,
        @Query("followingID") followingId: Long,
        @Query("followedID") followedId: Long,
    ): Response<Unit>

    @DELETE("segon_pix_auth/delete/follow")
    suspend fun deleteFollow(
        @Header("Authorization") token: String,
        @Query("followingID") followingId: Long,
        @Query("followedID") followedId: Long,
    ): Response<Unit>

    @Multipart
    @POST("segon_pix_auth/update/user/header")
    suspend fun updateUserHeader(
        @Header("Authorization") token: String,
        @Query("userID") userId: Long,
        @Part file: MultipartBody.Part,
    ): UserResponse

    @Multipart
    @POST("segon_pix_auth/update/user/icon")
    suspend fun updateUserIcon(
        @Header("Authorization") token: String,
        @Query("userID") userId: Long,
        @Part file: MultipartBody.Part,
    ): UserResponse

    @PUT("segon_pix/update/user")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Query("userID") userId: Long,
        @Query("name") name: String?,
        @Query("description") description: String?,
        @Query("birthday") birthday: Int?,
        @Query("email") email: String?,
    ): UserResponse
}
