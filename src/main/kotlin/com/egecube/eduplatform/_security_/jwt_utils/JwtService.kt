package com.egecube.eduplatform._security_.jwt_utils

import io.jsonwebtoken.Claims
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date
import kotlin.collections.HashMap


@Service
class JwtService {
//    @Value("\${jwt_timeout_h}")
    private var tokenTimeoutH: Int = 1

//    @Value("\${jwt_timeout_h}")
    private var tokenRefreshTimeoutH: Int = 12

    @Autowired
    private lateinit var jwtUtils: JwtUtils

    fun generateToken(
        claims: HashMap<String, Any>,
        userDetails: UserDetails
    ): String = jwtUtils.buildToken(
        claims, userDetails, tokenTimeoutH
    )

    fun generateToken(
        userDetails: UserDetails
    ): String = jwtUtils.buildToken(
        HashMap(), userDetails, tokenTimeoutH
    )

    fun generateRefreshToken(
        userDetails: UserDetails
    ): String = jwtUtils.generateRefreshToken(
        userDetails, tokenRefreshTimeoutH
    )

    fun isTokenExpired(token: String): Boolean =
        jwtUtils.extractClaim(token, Claims::getExpiration).before(Date())

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    // Defined methods

    fun extractUsername(token: String): String? = jwtUtils.extractClaim(
        token, Claims::getSubject
    )

    //...
}