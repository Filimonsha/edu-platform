package com.egecube.eduplatform.common.exceptionHandling

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class NoSuchElementHandler: ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [NoSuchElementException::class])
    protected fun handleResourceNotFount(
        exc: RuntimeException,
        req: WebRequest,
        method: HandlerMethod
    ): ResponseEntity<Any>? {
        logger.warn("Handled '$exc' at ${method.method}")
        val body = ErrorMessage(404, exc.message?: "Not found")
        return handleExceptionInternal(
            exc, body, HttpHeaders(), HttpStatus.NOT_FOUND, req
        )
    }
}
