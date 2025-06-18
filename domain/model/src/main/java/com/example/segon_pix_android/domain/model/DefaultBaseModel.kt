package com.example.segon_pix_android.domain.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefaultBaseModel(
    override val id: Long = 0,
    @SerialName("created_at")
    override val createdAt: Instant = Instant.DISTANT_PAST,
    @SerialName("updated_at")
    override val updatedAt: Instant = Instant.DISTANT_PAST,
    @SerialName("deleted_at")
    override val deletedAt: Instant? = null,
) : BaseModel
