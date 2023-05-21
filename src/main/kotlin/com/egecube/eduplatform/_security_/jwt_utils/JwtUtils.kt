package com.egecube.eduplatform._security_.jwt_utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.Date
import java.util.function.Function

@Component
class JwtUtils {
//    @Value("\${jwt_signin_key}")
    private var secretKey: String = "fkldsnfkjlsdnflksnhfdjkshfjdkshbfbkefsfbnnskefjksdbfjskbfjesfkhjsdf"

    private val signKey = Keys.hmacShaKeyFor(secretKey.toByteArray())
    private val signAlgorithm = SignatureAlgorithm.HS256

    fun buildToken(
        claims: Map<String, Any>,
        userDetails: UserDetails,
        expiredHours: Int
    ): String {
        val issuedAt: Long = System.currentTimeMillis()
        val expiredAt = issuedAt + expiredHours * 60 * 60 * 1000

        return Jwts.builder()
            .setSubject(userDetails.username)
            .setClaims(claims)
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

    fun generateRefreshToken(
        userDetails: UserDetails,
        refreshExpiredHours: Int
    ): String = buildToken(
        HashMap(),
        userDetails,
        refreshExpiredHours
    )
}