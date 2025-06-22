package com.example.dto

import com.example.segon_pix_android.domain.model.PostedImage
import com.example.segon_pix_android.domain.model.User
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("ID") val id: Long,
    @SerialName("Name") val name: String,
    @SerialName("Description") val description: String = "",
    @SerialName("Icon") val icon: String = "",
    @SerialName("Header_image") val headerImage: String = "",
    @SerialName("Birthday") val birthday: Int,
    @SerialName("posted_images") val postedImages: List<PostedImage> = emptyList(),
    @SerialName("liked_images") val likedImages: List<PostedImage> = emptyList(),
    @SerialName("follows") val follows: List<User> = emptyList(),
    @SerialName("followers") val followers: List<User> = emptyList(),
)

fun UserResponse.toDomainUser(): User =
    User(
        id = this.id,
        name = this.name,
        description = this.description,
        icon = this.icon,
        headerImage = this.headerImage,
        email = "",
        password = "",
        birthday = this.birthday,
        createdAt = Instant.DISTANT_PAST,
        updatedAt = Instant.DISTANT_PAST,
        deletedAt = null,
        postedImages = emptyList(),
        likedImages = emptyList(),
        follows = emptyList(),
        followers = emptyList(),
    )
