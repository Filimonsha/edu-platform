package com.egecube.eduplatform._security_.services

import com.egecube.eduplatform._security_.dto.AuthResponse
import com.egecube.eduplatform._security_.dto.LoginRequest
import com.egecube.eduplatform._security_.dto.RegisterRequest
import com.egecube.eduplatform._security_.jwt_utils.JwtService
import com.egecube.eduplatform._security_.user_data.UserAccount
import com.egecube.eduplatform._security_.user_data.UserAccountRepository
import com.egecube.eduplatform._security_.user_data.UserRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

// TODO("make full review and rewrite logic")

@Service
class UserAccountService {

    @Autowired
    private lateinit var userAccountRepository: UserAccountRepository
    @Autowired
    private lateinit var jwtService: JwtService
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager
    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    fun authenticateUser(request: LoginRequest): AuthResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.userMail,
                request.password
            )
        )
        // TODO("check for null")
        val user = userAccountRepository.findByEmail(request.userMail)
        val jwtToken = jwtService.generateToken(user!!)
        return AuthResponse(jwtToken)
    }

    fun registerUser(request: RegisterRequest): AuthResponse {
        val newUser = UserAccount.build().also {
            it.firstName = request.firstName
            it.lastName = request.lastName
            it.email = request.userMail
            it.phone = request.userPhone
            it.passWord = passwordEncoder.encode(request.password)
            it.role = UserRole.USER
        }

        userAccountRepository.save(newUser)
        val jwtToken = jwtService.generateToken(newUser)
        return AuthResponse(jwtToken)
    }
}