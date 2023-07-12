package com.hoony.example.controller

import com.hoony.example.config.Log
import com.hoony.example.service.TestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController(
  private val testService: TestService
) : Log {

  @PostMapping("/send-sqs-message")
  fun sendSqs() {
    testService.sendSqsMessage()
    logger.info("testEndpoint called")
  }

  @GetMapping("/get-sqs-message")
  fun testEndpoint() {
    testService.getSqsMessage()
    logger.info("testEndpoint called")
  }

}
