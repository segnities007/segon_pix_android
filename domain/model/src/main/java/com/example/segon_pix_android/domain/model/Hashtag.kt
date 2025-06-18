package com.example.segon_pix_android.domain.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hashtag(
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
    @SerialName("posted_images")
    val postedImages: List<PostedImage> = emptyList(),
)
