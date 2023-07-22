package com.egecube.eduplatform._security_.tokens

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


    @PostMapping(TokensRoutes.TOKENS)
    fun authenticateUser(
        @RequestBody request: LoginRequest
    ): ResponseEntity<AuthResponse> {
        return try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    request.userMail,
                    request.password
                )
            )
            val jwtToken = jwtService.generateToken(request.userMail)
            ResponseEntity.ok().body(AuthResponse(jwtToken))
        } catch (e: AuthenticationException) {
            ResponseEntity.badRequest().build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.badRequest().build()
        }
    }
}