package com.example.segon_pix_android.data.repoitory_impl

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

class ImageRepositoryImpl
    @Inject
    constructor(
        private val imageApiService: ImageApiService,
        private val tokenManager: AuthTokenManager,
    ) : ImageRepository {
        private suspend fun getAuthToken(): String =
            tokenManager.getToken()?.let {
                "Bearer $it"
            } ?: throw IllegalStateException("Auth token is not available")

        override suspend fun addImage(
            userId: Long,
            imageBytes: ByteArray,
            hashtags: List<String>,
        ): Long? =
            try {
                val requestBody = imageBytes.toRequestBody("image/*".toMediaTypeOrNull())
                val imagePart = MultipartBody.Part.createFormData("File", "image.jpg", requestBody)

                val response = imageApiService.addImage(getAuthToken(), userId, imagePart, hashtags)
                response
            } catch (e: Exception) {
                e.printStackTrace()
                null
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
                false
            }

        override suspend fun searchImagesByHashtag(hashtag: String): List<Image> =
            try {
                val response = imageApiService.searchImagesByHashtag(hashtag)
                response.images
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }

        override suspend fun getPopularImages(): List<Image> =
            try {
                val response = imageApiService.getPopularImages()
                response.images
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }

        override suspend fun getRecentImages(): List<Image> =
            try {
                val response = imageApiService.getRecentImages()
                response.images
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }

        override suspend fun getImageDetail(imageId: Long): PostedImage? =
            try {
                val response = imageApiService.getImageDetail(imageId)
                response
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
    }
