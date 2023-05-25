package com.egecube.eduplatform._security_.dto.requests

import com.egecube.eduplatform._security_.user_data.UserRole

data class ChangeRoleRequest (
    val userToChange: String,
    val newRole: UserRole
)
