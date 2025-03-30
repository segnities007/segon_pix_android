package com.example.segon_pix_android.domain.repository

import com.example.segon_pix_android.data.model.Hashtag
import java.io.File

interface PostedImageRepository {
    fun postImage(
        token: String,
        userID: Long,
        image: File,
        hashtags: List<Hashtag>,
    )

    fun getImageDetail(
        token: String,
        imageID: Long,
    )

    fun deleteImage(
        token: String,
        userID: Long,
        imageID: Long,
    )

    fun likeImage(
        token: String,
        userID: Long,
        imageID: Long,
    )
}
