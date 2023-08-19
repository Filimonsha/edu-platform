package com.egecube.eduplatform._security_.config

import com.egecube.eduplatform._security_.filters.JwtAuthFilter
import com.egecube.eduplatform._security_.routes.BaseRoute
import com.tngtech.archunit.thirdparty.com.google.common.collect.ImmutableList
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class FilterConfig(
    private val jwtAuthFilter: JwtAuthFilter,
    private val authProvider: AuthenticationProvider
) {

    @Order(1)
    @Bean
    fun apiFilterChain(http: HttpSecurity): SecurityFilterChain {
        // Disable csrf
        // Replace sessions with jwt
        http
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeHttpRequests()
            .requestMatchers("${BaseRoute.BASE_ROUTE}/**", "/swagger-ui/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration()
        config.allowedOrigins = ImmutableList.of("http://localhost:3000")
        config.allowCredentials = true
        config.allowedMethods = ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH")
        config.allowedHeaders = ImmutableList.of("Authorization", "Cache-Control", "Content-Type")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }

}