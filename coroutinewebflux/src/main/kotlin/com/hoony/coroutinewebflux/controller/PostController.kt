package com.hoony.coroutinewebflux.controller

import com.hoony.coroutinewebflux.service.PostService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class PostController(
    private val postService: PostService
) {

    @GetMapping("/test")
    suspend fun test() = postService.getPost()



}