package com.example.segon_pix_android.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
data class Hashtag
    @OptIn(ExperimentalTime::class)
    constructor(
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
