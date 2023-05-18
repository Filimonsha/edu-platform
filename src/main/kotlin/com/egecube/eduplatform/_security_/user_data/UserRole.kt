package com.egecube.eduplatform._security_.user_data

enum class UserRole {
    USER, // Can only access open data under auth condition
    ADMIN, // Can access and change all data
    MENTOR, // Can only read closed data and leave reviews
    TEACHER // Can read and manage closed data
}
