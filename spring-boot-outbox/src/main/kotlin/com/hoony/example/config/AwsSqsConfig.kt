package com.hoony.example.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.*
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsClient

@Configuration
class AwsConfig(
  @Value("\${aws.accessKey}") private val accessKey: String,
  @Value("\${aws.secretKey}") private val secretKey: String,
) {

  @Bean
  fun mySqsClient(credentialsProvider: AwsCredentialsProvider): SqsClient {
    return SqsClient.builder().credentialsProvider(credentialsProvider).region(Region.AP_NORTHEAST_2).build()
  }

  @Bean
  fun myAwsCredentialsProvider(): AwsCredentialsProvider {
    return StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey))
  }

}