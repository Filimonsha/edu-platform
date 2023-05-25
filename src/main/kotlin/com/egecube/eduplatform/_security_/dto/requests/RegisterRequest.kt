package com.egecube.eduplatform._security_.dto.requests

import org.springframework.lang.Nullable

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val userMail: String,
    @Nullable
    val userPhone: String?,
    val password: String
)
