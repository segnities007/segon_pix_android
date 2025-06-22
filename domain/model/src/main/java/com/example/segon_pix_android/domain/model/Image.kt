package com.example.segon_pix_android.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("ID")
    val id: Long = 0,
    @SerialName("URL")
    val url: String = "https://avatars.githubusercontent.com/u/134184436?v=4",
)
