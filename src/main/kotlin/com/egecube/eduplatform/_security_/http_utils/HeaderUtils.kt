package com.egecube.eduplatform._security_.http_utils

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component

@Component
class HeaderUtils {
    val authHeaderSign = "Authorization"
    val jwtPreIndex = "Bearer "

    fun getAuthHeader(request: HttpServletRequest): String? {
        return request.getHeader(authHeaderSign)
    }

    fun jwtHeaderCorrect(request: HttpServletRequest): Boolean {
        val authHeader = getAuthHeader(request)
        return authHeader?.startsWith(jwtPreIndex) ?: false
    }

    fun extractJwt(request: HttpServletRequest): String {
        val authHeader = getAuthHeader(request)
        return authHeader!!.substring(jwtPreIndex.length)
    }
}