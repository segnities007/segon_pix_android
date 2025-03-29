package com.example.segon_pix_android.data.model

import com.google.gson.annotations.SerializedName

data class PostedImage(
    @SerializedName("id") val id: Long,
    @SerializedName("url") val url: String,
    @SerializedName("user_id") val userId: Long,
    @SerializedName("post_user") val postUser: User,
    @SerializedName("object_name") val objectName: String,
    @SerializedName("likes") val likes: List<User> = emptyList(),
    @SerializedName("comments") val comments: List<Comment> = emptyList(),
    @SerializedName("hashtags") val hashtags: List<Hashtag> = emptyList(),
)
