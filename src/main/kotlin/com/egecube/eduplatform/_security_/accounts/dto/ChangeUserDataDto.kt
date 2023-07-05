package com.egecube.eduplatform._security_.accounts.dto

import com.egecube.eduplatform._security_.accounts.domain.UserRole
import org.springframework.lang.Nullable

data class ChangeUserDataDto(
    val userMail: String,
    @Nullable
    val userPhone: String?,
    val firstName: String,
    val lastName: String,
    val passWord: String,
    val role: UserRole
)
