package com.hoony.example.config

import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.cache.CacheManager
import javax.cache.Caching
import javax.cache.configuration.FactoryBuilder
import javax.cache.configuration.MutableCacheEntryListenerConfiguration
import javax.cache.configuration.MutableConfiguration
import javax.cache.expiry.CreatedExpiryPolicy
import javax.cache.expiry.Duration

object CacheNames {
  const val META_DATA_SERVICE_GET_CATEGORY: String = "cacheservice.category"
}

data class CacheSetting(
  val cacheNames: String,
  val config: MutableConfiguration<Any, Any>
)

@EnableCaching
@Configuration
class CacheConfig {

  @Bean
  fun customCacheManager( cacheSettings : List<CacheSetting>): CacheManager {
    val cacheManager: CacheManager = Caching.getCachingProvider().cacheManager

    cacheSettings.forEach { cacheSetting ->
      cacheManager.createCache(cacheSetting.cacheNames, cacheSetting.config)
    }
    return cacheManager
  }

  @Bean
  fun getCategoryCache( customCacheEntryListenerConfiguration : MutableCacheEntryListenerConfiguration<Any, Any> ) : CacheSetting {
    return CacheSetting(
      CacheNames.META_DATA_SERVICE_GET_CATEGORY,
      MutableConfiguration<Any, Any>()
        .setTypes(Any::class.java, Any::class.java)
        .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.ONE_MINUTE))
        .addCacheEntryListenerConfiguration( customCacheEntryListenerConfiguration )
        .setStoreByValue(true)
//        .setReadThrough(true)
//        .setWriteThrough(true)
    )
  }

  @Bean
  fun customCacheEntryListenerConfiguration() : MutableCacheEntryListenerConfiguration<Any, Any> {
    return MutableCacheEntryListenerConfiguration<Any, Any>(
      FactoryBuilder.factoryOf(SimpleCacheEntryListener::class.java),
      null,
      true,
      true
    )

  }
}