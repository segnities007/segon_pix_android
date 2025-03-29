package com.example.segon_pix_android.data.model

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("id") val id: Long,
    @SerializedName("posted_image_id") val postedImageId: Long,
    @SerializedName("user_id") val userId: Long,
    @SerializedName("message") val message: String,
)
