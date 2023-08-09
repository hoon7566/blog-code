package com.hoony.coroutinewebflux.config

import kotlinx.serialization.json.Json
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.http.codec.json.KotlinSerializationJsonDecoder
import org.springframework.http.codec.json.KotlinSerializationJsonEncoder
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
class CustomWebConfig(
) : WebFluxConfigurer {

  @Bean
  fun myKJson() : Json{
    return Json {
      ignoreUnknownKeys = true
      isLenient = true
      encodeDefaults = true
      prettyPrint = true
      coerceInputValues = true
      allowStructuredMapKeys = true
      classDiscriminator = "#class"
    }
  }

  override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
    configurer.defaultCodecs().configureDefaultCodec { KotlinSerializationJsonHttpMessageConverter(myKJson()) }
    configurer.defaultCodecs().kotlinSerializationJsonEncoder(KotlinSerializationJsonEncoder(myKJson()))
    configurer.defaultCodecs().kotlinSerializationJsonDecoder(KotlinSerializationJsonDecoder(myKJson()))
  }

}