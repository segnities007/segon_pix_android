package com.example.segon_pix_android.data.model

import com.google.gson.annotations.SerializedName

data class Hashtag(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("posted_images") val postedImages: List<PostedImage> = emptyList(),
)
