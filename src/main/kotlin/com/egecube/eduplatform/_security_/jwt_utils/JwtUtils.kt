package com.egecube.eduplatform._security_.jwt_utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.Date
import java.util.function.Function
import javax.crypto.SecretKey

@Component
class JwtUtils {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var jwtConfig: JwtConfiguration
    private lateinit var secretKey: String
    private lateinit var signKey: SecretKey

    private val signAlgorithm = SignatureAlgorithm.HS256

    @PostConstruct
    private fun loadValues() {
        secretKey = jwtConfig.signingKey
        signKey = Keys.hmacShaKeyFor(secretKey.toByteArray())
    }

    fun buildToken(
        claims: Map<String, Any>,
        userDetails: UserDetails,
        expiredHours: Int
    ): String {
        val issuedAt: Long = System.currentTimeMillis()
        val expiredAt = issuedAt + expiredHours * 60 * 60 * 1000
        logger.trace("Building token for user ${userDetails.username}")
        return Jwts.builder()
            .setClaims(claims)
            // Subject truly is id
            .setSubject(userDetails.username)
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
    ): T = claimsResolver.apply(extractAllClaims(token))

}