package com.egecube.eduplatform._security_.jwt_utils

import io.jsonwebtoken.Claims
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
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

    fun generateToken(
        claims: HashMap<String, Any>,
        userDetails: UserDetails
    ): String = jwtUtils.buildToken(
        claims, userDetails, tokenTimeoutH.toInt()
    )

    fun generateToken(
        userDetails: UserDetails
    ): String = jwtUtils.buildToken(
        HashMap(), userDetails, tokenTimeoutH.toInt()
    )

    fun isTokenExpired(token: String): Boolean =
        jwtUtils.extractClaim(token, Claims::getExpiration).before(Date())

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractId(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    // Defined methods

    fun extractId(token: String): String? = jwtUtils.extractClaim(
        token, Claims::getSubject
    )

    //...
}