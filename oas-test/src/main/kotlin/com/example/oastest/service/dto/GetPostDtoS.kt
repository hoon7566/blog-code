package com.example.oastest.service.dto

import java.time.LocalDateTime

data class GetPostDtoS(
    val postId: Long? = null,
    val title: String,
    val content: String,
    val authorId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)

data class CreatePostDtoS(
    val title: String,
    val content: String,
    val authorId: Long,
)
