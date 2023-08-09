package com.hoony.coroutinewebflux.exception

import com.hoony.coroutinewebflux.config.Log
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono
import java.time.LocalDateTime


@Component
class ExceptionHandler(
    errorAttributes: ErrorAttributes,
    applicationContext: ApplicationContext,
    serverCodecConfigurer: ServerCodecConfigurer
) : AbstractErrorWebExceptionHandler(errorAttributes, WebProperties.Resources(), applicationContext), Log {

    init {
        super.setMessageWriters(serverCodecConfigurer.writers)
        super.setMessageReaders(serverCodecConfigurer.readers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes): RouterFunction<ServerResponse> {
        return RouterFunctions.route(RequestPredicates.all()) { request: ServerRequest ->
            renderErrorResponse(request, errorAttributes)
        }
    }

    fun renderErrorResponse(request: ServerRequest, errorAttributes: ErrorAttributes): Mono<ServerResponse> {
        val errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults())
        val getException = errorAttributes.getError(request)
        val errorResponse = if ( getException is CustomException) {
            ErrorResponse(
                status = 500,
                message = "UnHandledException",
                path = errorPropertiesMap["path"] as? String ?: request.path(),
                requestId = errorPropertiesMap["requestId"] as? String ?: "",
            )
        } else {
            logger.error("UnHandledException {}, {}", getException.message, getException.stackTraceToString())
            ErrorResponse(
                errorPropertiesMap["status"] as? Int ?: 500,
                errorPropertiesMap["error"] as? String,
                errorPropertiesMap["message"] as? String,
                errorPropertiesMap["path"] as? String ?: request.path(),
                errorPropertiesMap["requestId"] as? String ?: "",
            )
        }

        return ServerResponse.status(errorResponse.status)
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON).bodyValue(errorResponse)
    }

    data class ErrorResponse(
        val status: Int,
        val error: String? = null,
        val message: String?,
        val path: String,
        val requestId: String,
        val timestamp: LocalDateTime = LocalDateTime.now()
    )
}