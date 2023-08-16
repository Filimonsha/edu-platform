package com.egecube.eduplatform._security_.tokens.dto

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String
)
