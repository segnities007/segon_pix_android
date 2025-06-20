package com.example.segon_pix_android.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("id")
    val id: Long,
    @SerialName("url")
    val url: String,
)
