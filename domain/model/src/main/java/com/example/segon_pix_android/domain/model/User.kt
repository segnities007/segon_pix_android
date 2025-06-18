package com.example.segon_pix_android.domain.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id")
    val id: Long,
    @SerialName("created_at")
    val createdAt: Instant,
    @SerialName("updated_at")
    val updatedAt: Instant,
    @SerialName("deleted_at")
    val deletedAt: Instant? = null,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String = "",
    @SerialName("icon")
    val icon: String = "",
    @SerialName("header_image")
    val headerImage: String = "",
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("birthday")
    val birthday: Int,
    @SerialName("posted_images")
    val postedImages: List<PostedImage> = emptyList(),
    @SerialName("liked_images")
    val likedImages: List<PostedImage> = emptyList(),
    @SerialName("follows")
    val follows: List<User> = emptyList(),
    @SerialName("followers")
    val followers: List<User> = emptyList(),
)
