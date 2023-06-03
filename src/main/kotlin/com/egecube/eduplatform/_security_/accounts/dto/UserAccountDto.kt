package com.egecube.eduplatform._security_.accounts.dto

import com.egecube.eduplatform._security_.accounts.domain.UserAccount
import com.egecube.eduplatform._security_.accounts.domain.UserRole
import org.springframework.lang.Nullable

data class UserAccountDto (
    val userId: Long,
    val userMail: String,
    @Nullable
    val userPhone: String?,
    val firstName: String,
    val lastName: String,
    val accountSuspended: Boolean,
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
