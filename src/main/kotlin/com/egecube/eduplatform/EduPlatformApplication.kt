package com.egecube.eduplatform

import com.egecube.eduplatform._security_.jwt_utils.JwtConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(JwtConfiguration::class)
class EduPlatformApplication

fun main(args: Array<String>) {
    runApplication<EduPlatformApplication>(*args)
}
