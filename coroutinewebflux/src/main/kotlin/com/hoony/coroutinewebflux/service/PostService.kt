package com.hoony.coroutinewebflux.service

import kotlinx.coroutines.*
import org.springframework.stereotype.Service
import kotlin.coroutines.suspendCoroutine


@Service
class PostService {

    suspend fun getPost() = coroutineScope {
        println("getPost")
        "getPost"
    }


}