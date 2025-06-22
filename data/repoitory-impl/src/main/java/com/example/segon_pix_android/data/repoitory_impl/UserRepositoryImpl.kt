package com.example.segon_pix_android.data.repoitory_impl

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import com.example.dto.toDomainUser
import com.example.segon_pix_android.data.remote.service.UserApiService
import com.example.segon_pix_android.domain.model.User
import com.example.segon_pix_android.domain.repository.AuthRepository
import com.example.segon_pix_android.domain.repository.UserRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSource
import okio.buffer
import okio.source
import okio.use

class UserRepositoryImpl(
    private val authRepository: AuthRepository,
    private val userApiService: UserApiService,
    private val tokenProvider: () -> String?,
    private val context: Context,
) : UserRepository {
    private fun getAuthToken(): String = tokenProvider()?.let { "Bearer $it" } ?: throw IllegalStateException("Auth token is not available")

    override suspend fun getSelf(): User {
        val response = userApiService.getUserPublic(authRepository.getSelfId())
        return response.toDomainUser()
    }

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
            Log.e("UserRepository", "Error updating user: ${e.message}")
            null
        }

    override suspend fun updateUserIcon(
        userId: Long,
        uri: Uri,
    ): User? =
        try {
            val imagePart =
                createMultipartImagePart(uri, "File", "user_icon.jpg")
                    ?: return null

            val response = userApiService.updateUserIcon(getAuthToken(), userId, imagePart)
            response.toDomainUser()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("UserRepository", "Error updating user icon: ${e.message}")
            null
        }

    override suspend fun updateUserHeader(
        userId: Long,
        uri: Uri,
    ): User? =
        try {
            val imagePart =
                createMultipartImagePart(uri, "File", "user_header.jpg")
                    ?: return null

            val response = userApiService.updateUserHeader(getAuthToken(), userId, imagePart)
            response.toDomainUser()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("UserRepository", "Error updating user header: ${e.message}")
            null
        }

    override suspend fun deleteUser(userId: Long): Boolean =
        try {
            val response = userApiService.deleteUser(getAuthToken(), userId)
            response.isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("UserRepository", "Error deleting user: ${e.message}")
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
            Log.e("UserRepository", "Error following user: ${e.message}")
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
            Log.e("UserRepository", "Error unfollowing user: ${e.message}")
            false
        }

    private fun createMultipartImagePart(
        uri: Uri,
        partName: String,
        defaultFileName: String,
    ): MultipartBody.Part? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            if (inputStream == null) {
                Log.e("UserRepository", "Failed to open input stream for Uri: $uri")
                return null
            }

            inputStream.use { stream ->
                val bufferedSource: BufferedSource = stream.source().buffer()
                val imageBytes = bufferedSource.readByteArray()
                val mediaType = context.contentResolver.getType(uri)?.toMediaTypeOrNull() ?: "image/*".toMediaTypeOrNull()
                val requestBody = imageBytes.toRequestBody(mediaType)
                val fileName = getFileNameFromUri(uri) ?: defaultFileName

                MultipartBody.Part.createFormData(partName, fileName, requestBody)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("UserRepository", "Error creating multipart image part for Uri: $uri - ${e.message}")
            null
        }
    }

    private fun getFileNameFromUri(uri: Uri): String? {
        var result: String? = null
        if (uri.scheme == "content") {
            context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (nameIndex != -1) {
                        result = cursor.getString(nameIndex)
                    }
                }
            }
        }
        if (result == null) {
            result = uri.lastPathSegment
        }
        return result
    }
}
