package com.example.oastest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OasTestApplication

fun main(args: Array<String>) {
    runApplication<OasTestApplication>(*args)
}
