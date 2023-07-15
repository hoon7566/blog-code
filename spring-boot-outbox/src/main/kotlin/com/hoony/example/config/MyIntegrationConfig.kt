package com.hoony.example.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableMBeanExport
import org.springframework.integration.annotation.InboundChannelAdapter
import org.springframework.integration.annotation.Poller
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.config.EnableIntegration
import org.springframework.integration.config.EnableIntegrationManagement
import org.springframework.integration.core.MessageSource
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.jpa.core.JpaExecutor
import org.springframework.integration.jpa.inbound.JpaPollingChannelAdapter
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceUnit

@EnableIntegration
@Configuration
@EnableIntegrationManagement
@EnableMBeanExport
class MyIntegrationConfig(

) : Log {

  @PersistenceUnit
  private lateinit var entityManagerFactory: EntityManagerFactory


  @Bean
  fun myIntegrationFlow(myHandler: MessageHandler, messageSource : MessageSource<*>): IntegrationFlow {

    return IntegrationFlows.from(
      messageSource
    ).handle(myHandler).get() }


  @Bean
  fun myInboundMessageMapper(): JpaPollingChannelAdapter {
    var jpaExecutor: JpaExecutor = JpaExecutor(entityManagerFactory).also { it.setJpaQuery("select m from Outbox m") }
    return JpaPollingChannelAdapter(jpaExecutor)
  }

  @Bean
  fun myChannel(): MessageChannel {
    return DirectChannel()
  }

  @Bean
  fun myHandler(): MessageHandler {
    return MessageHandler { message ->
      logger.info("message: $message")
    }
  }
}