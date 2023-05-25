package com.egecube.eduplatform._security_.dto.requests

data class LoginRequest(
    val userMail: String,
    val password: String
)
