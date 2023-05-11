package com.egecube.eduplatform.common

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

//    @ExceptionHandler()
//    fun catchResourceNotFoundException
}