package com.example.segon_pix_android.data.repoitory_impl

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import com.example.segon_pix_android.data.remote.AuthTokenManager
import com.example.segon_pix_android.data.remote.service.ImageApiService
import com.example.segon_pix_android.domain.model.Comment
import com.example.segon_pix_android.domain.model.Image
import com.example.segon_pix_android.domain.model.PostedImage
import com.example.segon_pix_android.domain.repository.ImageRepository
import jakarta.inject.Inject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSource
import okio.buffer
import okio.source
import okio.use

class ImageRepositoryImpl
    @Inject
    constructor(
        private val imageApiService: ImageApiService,
        private val tokenManager: AuthTokenManager,
        private val context: Context,
    ) : ImageRepository {
        private fun getAuthToken(): String =
            tokenManager.getToken()?.let {
                "Bearer $it"
            } ?: throw IllegalStateException("Auth token is not available")

        override suspend fun addImage(
            userId: Long,
            uri: Uri,
            hashtags: List<String>,
        ): Boolean =
            try {
                val inputStream =
                    context.contentResolver.openInputStream(uri) ?: run {
                        Log.e("ImageRepository", "Failed to open input stream for Uri: $uri")
                        return false
                    }

                inputStream.use { stream ->
                    val bufferedSource: BufferedSource = stream.source().buffer()
                    val imageBytes = bufferedSource.readByteArray()

                    val mediaType = context.contentResolver.getType(uri)?.toMediaTypeOrNull() ?: "image/*".toMediaTypeOrNull()

                    val requestBody = imageBytes.toRequestBody(mediaType)
                    val fileName = getFileName(uri)
                    val imagePart = MultipartBody.Part.createFormData("File", fileName, requestBody)

                    val response = imageApiService.addImage(getAuthToken(), userId, imagePart, hashtags)
                    if (response.isSuccessful) true else false
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ImageRepository", "Error uploading image: ${e.message}")
                false
            }

        private fun getFileName(uri: Uri): String {
            var result: String? = null
            if (uri.scheme == "content") {
                val cursor = context.contentResolver.query(uri, null, null, null, null)
                cursor?.use {
                    if (it.moveToFirst()) {
                        val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        if (nameIndex != -1) {
                            result = it.getString(nameIndex)
                        }
                    }
                }
            }
            if (result == null) {
                result = uri.lastPathSegment
            }
            return result ?: "image.jpg"
        }

        override suspend fun deleteImage(
            imageId: Long,
            userId: Long,
        ): Boolean =
            try {
                val response = imageApiService.deleteImage(getAuthToken(), userId, imageId)
                response.isSuccessful
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ImageRepository", "Error deleting image: ${e.message}")
                false
            }

        override suspend fun likeImage(
            userId: Long,
            imageId: Long,
        ): Boolean =
            try {
                val response = imageApiService.addLike(getAuthToken(), userId, imageId)
                response.isSuccessful
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ImageRepository", "Error liking image: ${e.message}")
                false
            }

        override suspend fun unlikeImage(
            userId: Long,
            imageId: Long,
        ): Boolean =
            try {
                val response = imageApiService.deleteLike(getAuthToken(), userId, imageId)
                response.isSuccessful
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ImageRepository", "Error unliking image: ${e.message}")
                false
            }

        override suspend fun addComment(
            postedImageId: Long,
            userId: Long,
            message: String,
        ): Comment? =
            try {
                val response = imageApiService.addComment(getAuthToken(), userId, postedImageId, message)
                response
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ImageRepository", "Error adding comment: ${e.message}")
                null
            }

        override suspend fun updateComment(
            userId: Long,
            commentId: Long,
            imageId: Long,
            newComment: String,
        ): Comment? =
            try {
                val response = imageApiService.updateComment(getAuthToken(), userId, commentId, imageId, newComment)
                response
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ImageRepository", "Error updating comment: ${e.message}")
                null
            }

        override suspend fun deleteComment(
            userId: Long,
            commentId: Long,
        ): Boolean =
            try {
                val response = imageApiService.deleteComment(getAuthToken(), userId, commentId)
                response.isSuccessful
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ImageRepository", "Error deleting comment: ${e.message}")
                false
            }

        override suspend fun searchImagesByHashtag(hashtag: String): List<Image> =
            try {
                val response = imageApiService.searchImagesByHashtag(hashtag)
                response.images
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ImageRepository", "Error searching images by hashtag: ${e.message}")
                emptyList()
            }

        override suspend fun getPopularImages(): List<Image> =
            try {
                val response = imageApiService.getPopularImages()
                response.images
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ImageRepository", "Error getting popular images: ${e.message}")
                emptyList()
            }

        override suspend fun getRecentImages(): List<Image> =
            try {
                val response = imageApiService.getRecentImages()
                response.images
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ImageRepository", "Error getting recent images: ${e.message}")
                emptyList()
            }

        override suspend fun getImageDetail(imageId: Long): PostedImage? =
            try {
                val response = imageApiService.getImageDetail(imageId)
                response
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ImageRepository", "Error getting image detail: ${e.message}")
                null
            }
    }
