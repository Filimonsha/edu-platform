package com.egecube.eduplatform._security_.tokens

import com.egecube.eduplatform._security_.accounts.UserAccountRepository
import com.egecube.eduplatform._security_.jwt_utils.JwtService
import com.egecube.eduplatform._security_.tokens.consts.TokensRoutes
import com.egecube.eduplatform._security_.tokens.dto.AuthResponse
import com.egecube.eduplatform._security_.tokens.dto.LoginRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.NoSuchElementException

@RestController
class TokensController {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var userAccountRepository: UserAccountRepository

    @Autowired
    private lateinit var jwtService: JwtService

    @PostMapping(TokensRoutes.TOKEN)
    fun authenticateUser(
        @PathVariable("id") userId: Long,
        @RequestBody request: LoginRequest
    ): AuthResponse? {
        try {
            val user = userAccountRepository.findById(userId).get()
            // Double check for id and username match
            if (user.username != request.userMail) return null
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    request.userMail,
                    request.password
                )
            )
            val jwtToken = jwtService.generateToken(user)
            return AuthResponse(jwtToken)
        } catch (e: AuthenticationException) {
            return null
        } catch (e: NoSuchElementException) {
            return null
        }
    }
}