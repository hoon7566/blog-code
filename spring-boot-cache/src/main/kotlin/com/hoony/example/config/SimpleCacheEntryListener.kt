package com.hoony.example.config

import org.springframework.stereotype.Component
import javax.cache.event.*

@Component
class SimpleCacheEntryListener :
  CacheEntryCreatedListener<Any, Any>,
  CacheEntryExpiredListener<Any, Any>,
    Log
{

  override fun onCreated(events: MutableIterable<CacheEntryEvent<out Any, out Any>>) {
    logger.info("[onCreated]")
    logger.info("[onCreated]")
    events.forEach { event ->
      logger.info("[onCreated] eventType: "+event.eventType)
      logger.info("[onCreated] oldValue: "+event.oldValue)
      logger.info("[onCreated] isOldValueAvailable: "+event.isOldValueAvailable)
      logger.info("[onCreated] key: "+event.key)
      logger.info("[onCreated] source: "+event.source)
      logger.info("[onCreated] value: "+event.value)
    }
  }

  override fun onExpired(events: MutableIterable<CacheEntryEvent<out Any, out Any>>) {
    logger.info("[onExpired]")
    events.forEach { event ->
      logger.info("[onExpired] eventType: "+event.eventType)
      logger.info("[onExpired] oldValue: "+event.oldValue)
      logger.info("[onExpired] isOldValueAvailable: "+event.isOldValueAvailable)
      logger.info("[onExpired] key: "+event.key)
      logger.info("[onExpired] source: "+event.source)
      logger.info("[onExpired] value: "+event.value)
    }
  }
}
