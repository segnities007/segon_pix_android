package com.example.segon_pix_android.domain.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostedImage(
    @SerialName("id")
    val id: Long = 0,
    @SerialName("created_at")
    val createdAt: Instant = Instant.DISTANT_PAST,
    @SerialName("updated_at")
    val updatedAt: Instant = Instant.DISTANT_PAST,
    @SerialName("deleted_at")
    val deletedAt: Instant? = null,
    @SerialName("url")
    val url: String = "https://avatars.githubusercontent.com/u/134184436?v=4",
    @SerialName("user_id")
    val userId: Long = 0,
    @SerialName("post_user")
    val postUser: User? = null,
    @SerialName("object_name")
    val objectName: String = "",
    @SerialName("likes")
    val likes: List<User> = emptyList(),
    @SerialName("comments")
    val comments: List<Comment> = emptyList(),
    @SerialName("hashtags")
    val hashtags: List<Hashtag> = emptyList(),
)
