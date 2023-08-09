package com.hoony.coroutinewebflux.controller

import com.hoony.coroutinewebflux.config.Log
import com.hoony.coroutinewebflux.service.PostService
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class PostController(
    private val postService: PostService
) : Log {

    @GetMapping("/test")
    suspend fun test() = postService.getPost()

    @GetMapping("/testCancel")
    suspend fun testCancel() = postService.getPostCancel()

    @GetMapping("/test2")
    suspend fun test2() = coroutineScope {

//        val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
//
//            println("test444")
//            println(coroutineContext)
//
//            println(throwable)
//
//        }

        // 새로운 스코프
//        CoroutineScope(coroutineExceptionHandler).launch { postService.getPostThrowException() }

        // 현재 스코프
        val postThrowException: String = try {
            postService.getPostThrowException()

        } catch (e: Exception) {
            println("test423412")
            println(e)
            postService.getPost()
        }

        postThrowException
    }
}