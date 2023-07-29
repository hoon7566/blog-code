package com.hoony.example.service

import com.hoony.example.config.Log
import com.hoony.example.repository.OutboxRepository
import com.hoony.example.repository.entity.OutboxEntity
import com.hoony.example.util.SqsService
import org.springframework.context.annotation.Bean
import org.springframework.integration.core.MessageSource
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.jpa.core.JpaExecutor
import org.springframework.integration.jpa.inbound.JpaPollingChannelAdapter
import org.springframework.integration.scheduling.PollerMetadata
import org.springframework.messaging.Message
import org.springframework.messaging.support.GenericMessage
import org.springframework.scheduling.support.PeriodicTrigger
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceUnit

@Component
class OutboxService(
  private val sqsService: SqsService,
  private val outboxRepository: OutboxRepository

) : Log {

  @PersistenceUnit
  private lateinit var entityManagerFactory: EntityManagerFactory

  @Bean
  fun myIntegrationFlow(): IntegrationFlow {
    return IntegrationFlows.from(
      MessageSource { outboxRepository.findAll().let { if (it.isEmpty()) return@let null else GenericMessage<List<OutboxEntity>>(it) } }
    ) { o ->
      o.poller(
        PollerMetadata()
          .apply { trigger = PeriodicTrigger(1, TimeUnit.SECONDS) }
      )
    }.handle { message ->
      logger.info("message: $message")


      (message.payload as List<*>).forEach {
        it as OutboxEntity
        val entityManager = entityManagerFactory.createEntityManager()
        entityManager.transaction.begin()
        sqsService.sendSqsMessage(it.topic, it.payload)
        outboxRepository.delete(it)
        entityManager.transaction.commit()
      }
    }.get()
  }


}