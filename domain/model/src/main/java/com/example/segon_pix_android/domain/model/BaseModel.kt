package com.example.segon_pix_android.domain.model

import kotlinx.datetime.Instant

interface BaseModel {
    val id: Long
    val createdAt: Instant
    val updatedAt: Instant
    val deletedAt: Instant?
}
