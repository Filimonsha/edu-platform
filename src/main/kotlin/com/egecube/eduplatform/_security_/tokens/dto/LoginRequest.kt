package com.egecube.eduplatform._security_.tokens.dto

data class LoginRequest(
    val userMail: String,
    val password: String
)
