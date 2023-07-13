package com.egecube.eduplatform._security_.config

import com.egecube.eduplatform._security_.filters.AllowCorsFilter
import com.egecube.eduplatform._security_.filters.JwtAuthFilter
import com.egecube.eduplatform._security_.routes.BaseRoute
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class FilterConfig(
    private val jwtAuthFilter: JwtAuthFilter,
    private val authProvider: AuthenticationProvider,
    private val allowCorsFilter: AllowCorsFilter
) {

    @Order(1)
    @Bean
    fun apiFilterChain(http: HttpSecurity): SecurityFilterChain {
        // Disable csrf
        // Replace sessions with jwt
        http
            .csrf()
            .disable()
            .cors()
            .and()
            .authorizeHttpRequests()
            .requestMatchers("${BaseRoute.BASE_ROUTE}/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterAfter(allowCorsFilter, jwtAuthFilter::class.java)

        return http.build()
    }

}