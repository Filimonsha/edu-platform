package com.egecube.eduplatform._security_.auth_controllers

import com.egecube.eduplatform._security_.dto.AuthResponse
import com.egecube.eduplatform._security_.dto.RegisterRequest
import com.egecube.eduplatform._security_.services.UserAccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth/register")
class RegController {

    @Autowired
    private lateinit var userAccountService: UserAccountService

    @PostMapping
    fun registerAccount(
        @RequestBody request: RegisterRequest
    ): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok().body(userAccountService.registerUser(request))
    }
}