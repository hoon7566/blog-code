package com.hoony.coroutinewebflux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoroutinewebfluxApplication

fun main(args: Array<String>) {
    runApplication<CoroutinewebfluxApplication>(*args)
}
