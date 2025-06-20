package com.example.segon_pix_android.domain.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id")
    val id: Long = 0,
    @SerialName("created_at")
    val createdAt: Instant = Instant.DISTANT_PAST,
    @SerialName("updated_at")
    val updatedAt: Instant = Instant.DISTANT_PAST,
    @SerialName("deleted_at")
    val deletedAt: Instant? = null,
    @SerialName("name")
    val name: String = "NONAME",
    @SerialName("description")
    val description: String = "",
    @SerialName("icon")
    val icon: String = "https://avatars.githubusercontent.com/u/134184436?v=4",
    @SerialName("header_image")
    val headerImage: String = "",
    @SerialName("email")
    val email: String = "NOEMAIL",
    @SerialName("password")
    val password: String = "NOPASSWORD",
    @SerialName("birthday")
    val birthday: Int = 10000101,
    @SerialName("posted_images")
    val postedImages: List<PostedImage> = emptyList(),
    @SerialName("liked_images")
    val likedImages: List<PostedImage> = emptyList(),
    @SerialName("follows")
    val follows: List<User> = emptyList(),
    @SerialName("followers")
    val followers: List<User> = emptyList(),
)
