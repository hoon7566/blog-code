package com.example.oastest.controller

import com.example.oastest.service.PostService
import com.example.oastest.service.dto.CreatePostDtoS
import com.example.oastest.service.dto.GetPostDtoS
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@RestController
@RequestMapping("/posts")
class PostController(
    val postService: PostService,
) {

    @GetMapping
    fun getPostAll()= kotlin.runCatching {
        postService.getPostAll().map { GetPostByIdResponse.fromDtoS(it) }
    }.fold(
        onSuccess = { ResponseEntity.ok(it) },
        onFailure = { ResponseEntity.badRequest().build() }
    )


    @GetMapping("/{id}")
    fun getPostById( @PathVariable id: Long)= kotlin.runCatching {
        postService.getPostById(id).map { GetPostByIdResponse.fromDtoS(it) }
    }.fold(
        onSuccess = { ResponseEntity.ok(it) },
        onFailure = { ResponseEntity.badRequest().build() }
    )

    data class GetPostByIdResponse(
        val postId: Long,
        val title: String,
        val content: String,
        val authorId: Long,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
    ) {
        companion object {
            fun fromDtoS(dtoS: GetPostDtoS) = GetPostByIdResponse(
                postId = dtoS.postId!!,
                title = dtoS.title,
                content = dtoS.content,
                authorId = dtoS.authorId,
                createdAt = dtoS.createdAt,
                updatedAt = dtoS.updatedAt,
            )
        }
    }

    data class CreatePostRequest(
        val title: String,
        val content: String,
        val authorId: Long,
    )

    @PostMapping()
    fun createPost(@RequestBody createPostReq : CreatePostRequest) = kotlin.runCatching {
        postService.createPost(
            CreatePostDtoS(
                title = createPostReq.title,
                content = createPostReq.content,
                authorId = createPostReq.authorId,
            )
        ).map { GetPostByIdResponse.fromDtoS(it) }
    }.fold(
        onSuccess = { ResponseEntity.ok(it) },
        onFailure = { ResponseEntity.badRequest().build() }
    )
}
