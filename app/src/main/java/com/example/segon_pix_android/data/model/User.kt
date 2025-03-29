package com.example.segon_pix_android.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("icon") val icon: String?,
    @SerializedName("header_image") val headerImage: String?,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("birthday") val birthday: Int,
    @SerializedName("posted_images") val postedImages: List<PostedImage> = emptyList(),
    @SerializedName("liked_images") val likedImages: List<PostedImage> = emptyList(),
    @SerializedName("follows") val follows: List<User> = emptyList(),
    @SerializedName("followers") val followers: List<User> = emptyList(),
)
