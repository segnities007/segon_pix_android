package com.example.segon_pix_android.domain.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    @SerialName("id")
    val id: Long,
    @SerialName("created_at")
    val createdAt: Instant,
    @SerialName("updated_at")
    val updatedAt: Instant,
    @SerialName("deleted_at")
    val deletedAt: Instant? = null,
    @SerialName("posted_image_id")
    val postedImageId: Long,
    @SerialName("user_id")
    val userId: Long,
    @SerialName("message")
    val message: String,
)
