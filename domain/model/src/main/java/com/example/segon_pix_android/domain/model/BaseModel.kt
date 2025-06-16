package com.example.segon_pix_android.domain.model

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

interface BaseModel {
    val id: Long

    @OptIn(ExperimentalTime::class)
    val createdAt: Instant

    @OptIn(ExperimentalTime::class)
    val updatedAt: Instant

    @OptIn(ExperimentalTime::class)
    val deletedAt: Instant?
}
