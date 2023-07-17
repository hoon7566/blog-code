package com.hoony.example.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.hoony.example.config.Log
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest
import software.amazon.awssdk.services.sqs.model.Message
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest
import software.amazon.awssdk.services.sqs.model.SendMessageRequest

@Component
class SqsService(
  private val sqsClient: SqsClient
): Log {

  fun sendSqsMessage(queueName: String, message: Any) {

    val getQueueRequest = GetQueueUrlRequest.builder()
      .queueName(queueName)
      .build()

    val queueUrl: String = sqsClient.getQueueUrl(getQueueRequest).queueUrl()

    val sendMsgRequest = SendMessageRequest.builder()
      .queueUrl(queueUrl)
      .messageBody(ObjectMapper().writeValueAsString(message))
      .delaySeconds(1)
      .build()

    sqsClient.sendMessage(sendMsgRequest)
  }

  fun getSqsMessage(queueName: String) : List<Message> {
    val getQueueRequest = GetQueueUrlRequest.builder()
      .queueName(queueName)
      .build()

    val queueUrl: String = sqsClient.getQueueUrl(getQueueRequest).queueUrl()

    val receiveMessageRequest = ReceiveMessageRequest.builder()
      .queueUrl(queueUrl)
      .maxNumberOfMessages(5)
      .build()

    return sqsClient.receiveMessage(receiveMessageRequest).messages()

  }
}