package com.hoony.example.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.*
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsClient

@Configuration
class AwsConfig {

  @Bean
  fun mySqsClient( credentialsProvider: AwsCredentialsProvider): SqsClient {

    return SqsClient.builder()
      .credentialsProvider(credentialsProvider)
      .region(Region.AP_NORTHEAST_2)
      .build()
  }

  @Bean
  fun myAwsCredentialsProvider(): AwsCredentialsProvider {
    val credentials = AwsBasicCredentials.create("accessKey", "secretKey")
    return StaticCredentialsProvider.create(credentials)
  }

}