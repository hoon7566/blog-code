package com.example.oastest.service

import com.example.oastest.repository.PostRepository
import com.example.oastest.repository.entity.PostEntity
import com.example.oastest.service.dto.CreatePostDtoS
import com.example.oastest.service.dto.GetPostDtoS
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PostService(
    val postRepository: PostRepository,
) {

    @Transactional(readOnly = true)
    fun getPostById(id: Long): Mono<GetPostDtoS> {
        return postRepository.findById(id).map {
            it.toDtoS()
        }
    }

    @Transactional(readOnly = true)
    fun getPostAll(): Flux<GetPostDtoS> {
        return postRepository.findAll().map { 
            it.toDtoS()
        }
    }
    
    @Transactional
    fun createPost(post: CreatePostDtoS): Mono<GetPostDtoS> {
        return postRepository.save(
            PostEntity(
                title = post.title,
                content = post.content,
                authorId = post.authorId,
            )
        ).map {
            it.toDtoS()
        }
    }

    fun PostEntity.toDtoS() = GetPostDtoS(
        postId = this.id,
        title = this.title,
        content = this.content,
        authorId = this.authorId,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    )

}