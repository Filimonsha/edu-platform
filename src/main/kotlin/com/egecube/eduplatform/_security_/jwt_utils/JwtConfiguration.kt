package com.egecube.eduplatform._security_.jwt_utils

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "jwt")
data class JwtConfiguration @ConstructorBinding constructor(
    val signingKey: String,
    val timeoutH: String
)