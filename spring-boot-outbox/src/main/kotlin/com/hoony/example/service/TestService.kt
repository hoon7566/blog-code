package com.hoony.example.service

import com.hoony.example.config.Log
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest
import software.amazon.awssdk.services.sqs.model.ListQueuesRequest
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest
import software.amazon.awssdk.services.sqs.model.SendMessageRequest


@Service
class TestService(
  private val sqsClient: SqsClient
): Log {

  fun sendSqsMessage() {
    logger.info("sendSqsMessage called")
    val queueName = "my-queue"

    val getQueueRequest = GetQueueUrlRequest.builder()
      .queueName(queueName)
      .build()

    val queueUrl: String = sqsClient.getQueueUrl(getQueueRequest).queueUrl()
    val sendMsgRequest = SendMessageRequest.builder()
      .queueUrl(queueUrl)
      .messageBody("hoony message")
      .delaySeconds(5)
      .build()

    sqsClient.sendMessage(sendMsgRequest)
    sqsClient.close()

  }

  fun getSqsMessage(){
    logger.info("get sqsMessage called")
    val queueName = "my-queue"
    val getQueueRequest = GetQueueUrlRequest.builder()
      .queueName(queueName)
      .build()

    val queueUrl: String = sqsClient.getQueueUrl(getQueueRequest).queueUrl()

    val receiveMessageRequest = ReceiveMessageRequest.builder()
      .queueUrl(queueUrl)
      .maxNumberOfMessages(5)
      .build()

    sqsClient.receiveMessage(receiveMessageRequest).messages().forEach {
      logger.info(it.body())
    }
  }


}