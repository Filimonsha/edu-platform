package com.egecube.eduplatform._security_.jwt_utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Function
import javax.crypto.SecretKey

@Component
class JwtUtils(
    private val jwtConfig: JwtConfiguration
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val signAlgorithm = SignatureAlgorithm.HS512

    private lateinit var secretKey: String
    private lateinit var signKey: SecretKey

    @PostConstruct
    private fun loadValues() {
        secretKey = jwtConfig.signingKey
        signKey = Keys.hmacShaKeyFor(secretKey.toByteArray())
    }

    fun buildToken(
        claims: Map<String, Any>,
        userMail: String,
        expiredHours: Int
    ): String {
        val issuedAt: Long = System.currentTimeMillis()
        val expiredAt = issuedAt + expiredHours * 60 * 60 * 1000
        logger.trace("Building token for user $userMail")
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userMail)
            .setIssuedAt(Date(issuedAt))
            .setExpiration(Date(expiredAt))
            .signWith(signKey, signAlgorithm)
            .compact()
    }

    private fun extractAllClaims(token: String): Claims =
        Jwts.parserBuilder()
            .setSigningKey(signKey)
            .build()
            .parseClaimsJws(token)
            .body

    fun <T> extractClaim(
        token: String, claimsResolver: Function<Claims, T>
    ): T? {
        return try {
            claimsResolver.apply(extractAllClaims(token))
        } catch (e: JwtException) {
            null
        }
    }

}