package com.example.segon_pix_android.domain

import com.example.segon_pix_android.data.model.Comment
import com.example.segon_pix_android.data.model.Hashtag
import com.example.segon_pix_android.data.model.PostedImage
import com.example.segon_pix_android.data.model.User
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import java.io.File

interface ApiServices {
    // POST

    @POST("/add/user")
    suspend fun postUser(user: User): Response<User>

    @POST("/add/image")
    suspend fun postImage(
        token: String,
        image: File,
        hashtags: List<Hashtag>,
    ): Response<PostedImage>

    @POST("/add/comment")
    suspend fun postComment(
        token: String,
        userID: String,
        imageID: String,
        comment: String,
    )

    // GET

    @GET("/get/user")
    suspend fun getUser(userID: String): Response<User>

    @GET("/get/user")
    suspend fun getUserWithToken(
        token: String,
        userID: String,
        email: String,
        password: String,
    ): Response<User>

    @GET("/get/image")
    suspend fun getImage(hashtag: Hashtag): Response<PostedImage>

    @GET("/get/comment")
    suspend fun getComment(): Response<Comment>

    @GET("/get/list/search")
    suspend fun getPostedImageWithHashtag(): Response<List<String>>

    @GET("/get/like/list")
    suspend fun getImagesSortedByLikes(): Response<List<String>>

    @GET("/get/list/resent")
    suspend fun getLatestImages(): Response<List<String>>

    @GET("/get/image_detail")
    suspend fun getImageDetail(imageID: String): Response<PostedImage>

    // UPDATE

    @PUT("/update/user")
    suspend fun updateUser(
        token: String,
        userID: String,
        name: String,
        description: String,
        birthday: String,
        email: String,
    )

    @PUT("/update/comment")
    suspend fun updateComment(
        token: String,
        userID: String,
        commentID: String,
        imageID: String,
        newComment: String,
    )

    @PUT("/update/user/header")
    suspend fun updateUserHeader(
        token: String,
        userID: String,
        headerImage: File,
    )

    @PUT("/update/user/icon")
    suspend fun updateUserIcon(
        token: String,
        userID: String,
        icon: File,
    )

    // DELETE

    @DELETE("/delete/user")
    suspend fun deleteUser(
        token: String,
        userID: String,
    ): Response<Boolean>

    @DELETE("/delete/image")
    suspend fun deleteImage(
        token: String,
        userID: String,
        imageID: String,
    ): Response<Boolean>

    @DELETE("/delete/comment")
    suspend fun deleteComment(
        token: String,
        userID: String,
        commentID: String,
    ): Response<Boolean>

    @DELETE("/delete/like")
    suspend fun removeLike(
        token: String,
        userID: String,
        imageID: String,
    ): Response<Boolean>

    // OTHER

    @POST("/add/follow")
    suspend fun follow(
        token: String,
        followingID: String,
        followedID: String,
    ): Response<Boolean>

    @DELETE("/delete/follow")
    suspend fun unFollow(
        token: String,
        followingID: String,
        followedID: String,
    ): Response<Boolean>
}
