package com.egecube.eduplatform._security_.jwt_utils

import com.egecube.eduplatform._security_.accounts.domain.UserRole
import io.jsonwebtoken.Claims
import org.springframework.stereotype.Service
import java.util.*


@Service
class JwtService(
    private val jwtConfig: JwtConfiguration,
    private val jwtUtils: JwtUtils,
//    private val jwtRepository: JwtRepository
) {

    fun generateRefreshToken(
        userMail: String,
        userId: Long,
        userRights: UserRole
    ): String = jwtUtils.buildToken(
        hashMapOf(
            "userId" to userId.toString(),
            "userRights" to userRights.name,
            "type" to "refresh"
        ),
        userMail,
        jwtConfig.refreshTimeoutHours.toInt() * 60
    )


    fun generateAccessToken(
        userMail: String,
        userId: Long,
        userRights: UserRole
    ): String = jwtUtils.buildToken(
        hashMapOf(
            "userId" to userId.toString(),
            "userRights" to userRights.name,
            "type" to "access"
        ),
        userMail,
        jwtConfig.accessTimeoutMinutes.toInt()
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

    fun extractClaim(token: String, claim: String): String? = jwtUtils
        .extractAllClaims(token)[claim]
        .toString()
}