package com.example.segon_pix_android.data.remote.service

import com.example.dto.ImageListResponse
import com.example.segon_pix_android.domain.model.Comment
import com.example.segon_pix_android.domain.model.PostedImage
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

interface ImageApiService {
    @Multipart
    @POST("segon_pix_auth/add/image")
    suspend fun addImage(
        @Header("Authorization") token: String,
        @Query("userID") userId: Long,
        @Part file: MultipartBody.Part,
        @Part("Hashtags") hashtags: List<String>,
    ): Long

    @DELETE("segon_pix_auth/delete/image")
    suspend fun deleteImage(
        @Header("Authorization") token: String,
        @Query("userID") userId: Long,
        @Query("imageID") imageId: Long,
    ): Response<Unit>

    @POST("segon_pix_auth/add/like")
    suspend fun addLike(
        @Header("Authorization") token: String,
        @Query("userID") userId: Long,
        @Query("imageID") imageId: Long,
    ): Response<Unit>

    @DELETE("segon_pix_auth/delete/like")
    suspend fun deleteLike(
        @Header("Authorization") token: String,
        @Query("userID") userId: Long,
        @Query("imageID") imageId: Long,
    ): Response<Unit>

    @POST("segon_pix_auth/add/comment")
    suspend fun addComment(
        @Header("Authorization") token: String,
        @Query("userID") userId: Long,
        @Query("imageID") imageId: Long,
        @Query("comment") message: String,
    ): Comment

    @PUT("segon_pix_auth/update/comment")
    suspend fun updateComment(
        @Header("Authorization") token: String,
        @Query("userID") userId: Long,
        @Query("commentID") commentId: Long,
        @Query("imageID") imageId: Long,
        @Query("newComment") newComment: String,
    ): Comment

    @DELETE("segon_pix_auth/delete/comment")
    suspend fun deleteComment(
        @Header("Authorization") token: String,
        @Query("userID") userId: Long,
        @Query("commentID") commentId: Long,
    ): Response<Unit>

    @GET("segon_pix/get/list/search")
    suspend fun searchImagesByHashtag(
        @Query("Hashtag") hashtag: String,
    ): ImageListResponse

    @GET("segon_pix/get/list/like")
    suspend fun getPopularImages(): ImageListResponse

    @GET("segon_pix/get/list/recent")
    suspend fun getRecentImages(): ImageListResponse

    @GET("segon_pix/get/image_detail")
    suspend fun getImageDetail(
        @Query("imageID") imageId: Long,
    ): PostedImage
}
