package com.example.segon_pix_android.domain.repository

interface UserRepository {
    fun verifyAddUser(
        name: String,
        description: String,
        email: String,
        password: String,
        birthday: Int,
        code: String,
    )

    fun follow(
        followingID: Long,
        followedID: Long,
    )

    fun updateHeaderImage(
        token: String,
        id: Long,
        headerImage: String,
    )

    fun updateUserIcon(
        token: String,
        id: Long,
        icon: String,
    )

    fun getUser(id: Long)

    fun getUser(
        token: String,
        id: Long,
        email: String,
        password: String,
    )

    fun updateUser(
        token: String,
        id: Long,
        name: String,
        description: String,
        email: String,
    )
}
