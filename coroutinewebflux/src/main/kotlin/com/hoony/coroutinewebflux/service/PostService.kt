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

    suspend fun getPostCancel() = coroutineScope {



        this.coroutineContext.cancel(

        )
    }

    suspend fun getPostThrowException() = coroutineScope {


        val a= async ( Dispatchers.IO ){
            println(this.coroutineContext)
            println("a"+Thread.currentThread().name)
            delay(1000)
            println("test1")
            ""
        }
        val b= async ( Dispatchers.IO ){
            println(this.coroutineContext)
            println("b"+Thread.currentThread().name)
            delay(1000)
            println("test2")
            ""
        }

        val c= async ( Dispatchers.IO ){
            println(this.coroutineContext)
            println("c"+Thread.currentThread().name)
            delay(1000)
            println("test3")
            ""
        }


        throw Exception("getPostThrowException")


        "aaa"
    }





}