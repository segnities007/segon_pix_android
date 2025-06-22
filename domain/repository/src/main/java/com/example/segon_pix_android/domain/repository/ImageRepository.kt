package com.example.segon_pix_android.domain.repository

import android.net.Uri
import com.example.segon_pix_android.domain.model.Comment
import com.example.segon_pix_android.domain.model.Image
import com.example.segon_pix_android.domain.model.PostedImage

interface ImageRepository {
    suspend fun addImage(
        userId: Long,
        uri: Uri,
        hashtags: List<String>,
    ): Boolean

    suspend fun deleteImage(
        imageId: Long,
        userId: Long,
    ): Boolean

    suspend fun likeImage(
        userId: Long,
        imageId: Long,
    ): Boolean

    suspend fun unlikeImage(
        userId: Long,
        imageId: Long,
    ): Boolean

    suspend fun addComment(
        postedImageId: Long,
        userId: Long,
        message: String,
    ): Comment?

    suspend fun updateComment(
        userId: Long,
        commentId: Long,
        imageId: Long,
        newComment: String,
    ): Comment?

    suspend fun deleteComment(
        userId: Long,
        commentId: Long,
    ): Boolean

    suspend fun getImages(
        hashtag: String = "",
        like: Boolean = false,
        search: Boolean = true,
        imageId: Long = -1,
        likeNum: Int = -1,
    ): List<Image>

    suspend fun getImageDetail(imageId: Long): PostedImage?
}
