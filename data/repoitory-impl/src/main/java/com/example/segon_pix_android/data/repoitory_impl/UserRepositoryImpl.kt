package com.example.segon_pix_android.data.repoitory_impl

import com.example.dto.toDomainUser
import com.example.segon_pix_android.data.remote.service.UserApiService
import com.example.segon_pix_android.domain.model.User
import com.example.segon_pix_android.domain.repository.UserRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class UserRepositoryImpl(
    private val userApiService: UserApiService,
    private val tokenProvider: () -> String?,
) : UserRepository {
    private fun getAuthToken(): String = tokenProvider()?.let { "Bearer $it" } ?: throw IllegalStateException("Auth token is not available")

    override suspend fun updateUser(
        userId: Long,
        name: String?,
        description: String?,
        birthday: Int?,
        email: String?,
    ): User? =
        try {
            val response =
                userApiService.updateUser(
                    getAuthToken(),
                    userId,
                    name,
                    description,
                    birthday,
                    email,
                )
            response.toDomainUser()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    override suspend fun updateUserIcon(
        userId: Long,
        imageBytes: ByteArray,
    ): User? =
        try {
            val requestBody = imageBytes.toRequestBody("image/*".toMediaTypeOrNull())
            val imagePart = MultipartBody.Part.createFormData("File", "user_icon.jpg", requestBody)
            val response = userApiService.updateUserIcon(getAuthToken(), userId, imagePart)
            response.toDomainUser()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    override suspend fun updateUserHeader(
        userId: Long,
        imageBytes: ByteArray,
    ): User? =
        try {
            val requestBody = imageBytes.toRequestBody("image/*".toMediaTypeOrNull())
            val imagePart = MultipartBody.Part.createFormData("File", "user_header.jpg", requestBody)
            val response = userApiService.updateUserHeader(getAuthToken(), userId, imagePart)
            response.toDomainUser()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    override suspend fun deleteUser(userId: Long): Boolean =
        try {
            val response = userApiService.deleteUser(getAuthToken(), userId)
            response.isSuccessful // HTTPステータスコードが2xxならtrue
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun followUser(
        followingId: Long,
        followedId: Long,
    ): Boolean =
        try {
            val response = userApiService.addFollow(getAuthToken(), followingId, followedId)
            response.isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun unfollowUser(
        followingId: Long,
        followedId: Long,
    ): Boolean =
        try {
            val response = userApiService.deleteFollow(getAuthToken(), followingId, followedId)
            response.isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}
