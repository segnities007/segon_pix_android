package com.example.dto

import com.example.segon_pix_android.domain.model.Image
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageListResponse(
    @SerialName("images") val images: List<Image>,
)
