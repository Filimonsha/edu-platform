package com.egecube.eduplatform._security_.filters

import com.egecube.eduplatform._security_.jwt_utils.JwtService
import com.egecube.eduplatform._security_.user_data.UserAccountRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter : OncePerRequestFilter() {

    private val authHeaderSign = "Authorization"
    private val jwtPreIndex = "Bearer "

    @Autowired
    private lateinit var jwtService: JwtService

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // Check token exists
        val authHeader: String? = request.getHeader(authHeaderSign)
        if (authHeader.isNullOrEmpty() || !authHeader.startsWith(jwtPreIndex)) {
            filterChain.doFilter(request, response)
            return
        }

        val jwt = authHeader.substring(jwtPreIndex.length)
        val userMail = jwtService.extractUsername(jwt)
        if (!userMail.isNullOrBlank() &&
            SecurityContextHolder.getContext().authentication == null
        ) {
            // Update auth context for filters
            val userDetails = userDetailsService.loadUserByUsername(userMail)
            if (jwtService.isTokenValid(jwt, userDetails)) {
                val auth = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                )
                auth.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = auth
            }
        }
        filterChain.doFilter(request, response)
    }
}
