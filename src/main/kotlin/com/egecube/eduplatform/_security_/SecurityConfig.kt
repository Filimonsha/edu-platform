package com.egecube.eduplatform._security_

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

//@Configuration
//@EnableWebSecurity
//class MultiHttpSecurityConfig {
//    @Bean
//    fun userDetailsService(): UserDetailsService {
//        val users: User.UserBuilder = User.withDefaultPasswordEncoder()
//        val manager = InMemoryUserDetailsManager()
//        manager.createUser(users.username("user").password("password").roles("USER").build())
//        manager.createUser(users.username("admin").password("password").roles("USER","ADMIN").build())
//        return manager
//    }
//
//    @Order(1)
//    @Bean
//    fun apiFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .authorizeRequests()
//            .anyRequest()
//            .authenticated()
//            .and()
//            .formLogin()
//        return http.build()
//    }
//}