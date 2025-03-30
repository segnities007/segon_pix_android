package com.example.segon_pix_android.domain.repository

import com.example.segon_pix_android.data.model.Comment

interface CommentRepository {
    fun postComment(
        token: String,
        userID: Long,
        imageID: Long,
        comment: Comment,
    )

    fun updateComment(
        token: String,
        userID: Long,
        commentID: Long,
        imageID: Long,
        updatedComment: Comment,
    )

    fun deleteComment(
        token: String,
        userID: Long,
        commentID: Long,
    )
}
