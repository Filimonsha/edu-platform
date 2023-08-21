package com.egecube.eduplatform._security_.accounts.dto

import com.egecube.eduplatform._security_.accounts.domain.UserAccount
import com.egecube.eduplatform._security_.accounts.domain.UserRole
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.lang.Nullable

data class UserAccountDto (
    @Schema(name = "Personal generated sequence id")
    val userId: Long,
    @Schema(name = "Unique (nick)name for user, email")
    val userMail: String,
    @Nullable
    @Schema(name = "Phone number", required = false)
    val userPhone: String?,
    @Schema(name = "Credentials: first name")
    val firstName: String,
    @Schema(name = "Credentials: last name")
    val lastName: String,
    @Schema(name = "Used for banning users from access to site", required = false)
    val accountSuspended: Boolean,
    @Schema(name = "Defines user rights for content creation", required = false)
    val userRole: UserRole
) {
    constructor(user: UserAccount) : this(
        userId = user.id,
        accountSuspended = user.accountSuspended,
        firstName = user.firstName,
        lastName = user.lastName,
        userMail = user.email,
        userPhone = user.phone,
        userRole = user.role
    )
}
