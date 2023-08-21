package com.egecube.eduplatform

import com.egecube.eduplatform._security_.jwt_utils.JwtConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.web.bind.annotation.CrossOrigin

@SpringBootApplication
@EnableConfigurationProperties(JwtConfiguration::class)
@EnableMethodSecurity(securedEnabled = true)
//@EnableMethodSecurity(securedEnabled = true)
class EduPlatformApplication

fun main(args: Array<String>) {
    runApplication<EduPlatformApplication>(*args)
}
