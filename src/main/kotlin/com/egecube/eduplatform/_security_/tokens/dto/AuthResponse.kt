package com.egecube.eduplatform._security_.tokens.dto

import com.egecube.eduplatform._security_.accounts.dto.UserAccountDto

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
    val userData: UserAccountDto? = null
)
