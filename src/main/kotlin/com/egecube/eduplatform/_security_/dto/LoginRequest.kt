package com.egecube.eduplatform._security_.dto

data class LoginRequest(
    val userMail: String,
    val password: String
)
