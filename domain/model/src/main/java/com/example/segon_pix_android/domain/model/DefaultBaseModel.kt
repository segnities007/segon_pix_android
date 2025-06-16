package com.example.segon_pix_android.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
data class DefaultBaseModel
    @OptIn(ExperimentalTime::class)
    constructor(
        override val id: Long = 0,
        @SerialName("created_at")
        override val createdAt: Instant = Instant.DISTANT_PAST,
        @SerialName("updated_at")
        override val updatedAt: Instant = Instant.DISTANT_PAST,
        @SerialName("deleted_at")
        override val deletedAt: Instant? = null,
    ) : BaseModel
