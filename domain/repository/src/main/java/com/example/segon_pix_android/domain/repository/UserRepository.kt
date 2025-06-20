package com.example.segon_pix_android.domain.repository

import com.example.segon_pix_android.domain.model.User

interface UserRepository {
    suspend fun getSelf(): User

    suspend fun updateUser(
        userId: Long,
        name: String? = null,
        description: String? = null,
        birthday: Int? = null,
        email: String? = null,
    ): User?

    suspend fun updateUserIcon(
        userId: Long,
        imageBytes: ByteArray,
    ): User?

    suspend fun updateUserHeader(
        userId: Long,
        imageBytes: ByteArray,
    ): User?

    suspend fun deleteUser(userId: Long): Boolean

    suspend fun followUser(
        followingId: Long,
        followedId: Long,
    ): Boolean

    suspend fun unfollowUser(
        followingId: Long,
        followedId: Long,
    ): Boolean
}
