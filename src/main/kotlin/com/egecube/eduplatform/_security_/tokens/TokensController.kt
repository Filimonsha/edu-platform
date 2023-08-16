package com.egecube.eduplatform._security_.tokens

import com.egecube.eduplatform._security_.accounts.domain.UserAccount
import com.egecube.eduplatform._security_.jwt_utils.JwtService
import com.egecube.eduplatform._security_.tokens.consts.TokensRoutes
import com.egecube.eduplatform._security_.tokens.dto.AuthResponse
import com.egecube.eduplatform._security_.tokens.dto.LoginRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TokensController(
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService
) {


    @PostMapping(TokensRoutes.REF_TOKENS)
    fun authenticateUser(
        @RequestBody request: LoginRequest
    ): ResponseEntity<AuthResponse> {
        return try {
            val authAction = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    request.userMail,
                    request.password
                )
            )
            val auth = authAction.principal as UserAccount
            val accessToken = jwtService.generateAccessToken(auth.email, auth.id, auth.role)
            val refreshToken = jwtService.generateRefreshToken(auth.email, auth.id, auth.role)
            // clear refresh tokens for user and add one

            ResponseEntity.ok().body(AuthResponse(accessToken, refreshToken))
        } catch (e: AuthenticationException) {
            ResponseEntity.badRequest().build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.badRequest().build()
        }
    }
}