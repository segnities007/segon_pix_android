package com.example.segon_pix_android.domain.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostedImage(
    @SerialName("id")
    val id: Long,
    @SerialName("created_at")
    val createdAt: Instant,
    @SerialName("updated_at")
    val updatedAt: Instant,
    @SerialName("deleted_at")
    val deletedAt: Instant? = null,
    @SerialName("url")
    val url: String,
    @SerialName("user_id")
    val userId: Long,
    @SerialName("post_user")
    val postUser: User? = null,
    @SerialName("object_name")
    val objectName: String,
    @SerialName("likes")
    val likes: List<User> = emptyList(),
    @SerialName("comments")
    val comments: List<Comment> = emptyList(),
    @SerialName("hashtags")
    val hashtags: List<Hashtag> = emptyList(),
)
