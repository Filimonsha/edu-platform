package com.egecube.eduplatform._security_.accounts.domain

enum class UserRole {
    USER, // Can only access open data under auth condition
    ADMIN // Can access and change all data
}
