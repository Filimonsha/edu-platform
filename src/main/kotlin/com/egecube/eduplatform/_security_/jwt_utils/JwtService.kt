package com.egecube.eduplatform._security_.jwt_utils

import io.jsonwebtoken.Claims
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Date
import kotlin.collections.HashMap


@Service
class JwtService {

    @Autowired
    private lateinit var jwtConfig: JwtConfiguration
    private lateinit var tokenTimeoutH: String

    @Autowired
    private lateinit var jwtUtils: JwtUtils

    @PostConstruct
    fun loadValues() {
        tokenTimeoutH = jwtConfig.timeoutH
    }

//    fun generateToken(
//        claims: HashMap<String, Any>,
//        userMail: String
//    ): String = jwtUtils.buildToken(
//        claims, userMail, tokenTimeoutH.toInt()
//    )

    fun generateToken(
        userMail: String
    ): String = jwtUtils.buildToken(
        HashMap(), userMail, tokenTimeoutH.toInt()
    )

    fun isTokenExpired(token: String): Boolean {
        return jwtUtils.extractClaim(token, Claims::getExpiration)?.before(Date()) ?: true
    }

    fun isTokenValid(token: String, userMail: String): Boolean {
        val username = extractUserMail(token)
        return username == userMail && !isTokenExpired(token)
    }

    // Defined methods

    fun extractUserMail(token: String): String? = jwtUtils.extractClaim(
        token, Claims::getSubject
    )

    //...
}