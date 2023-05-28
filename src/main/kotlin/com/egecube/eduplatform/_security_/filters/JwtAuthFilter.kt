package com.egecube.eduplatform._security_.filters

import com.egecube.eduplatform._security_.access_rights_utils.AccessRightsService
import com.egecube.eduplatform._security_.jwt_utils.JwtService
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

    @Autowired
    private lateinit var accessRightsService: AccessRightsService

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Autowired
    private lateinit var jwtService: JwtService

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = accessRightsService.extractJwtIfPresentInRequest(request)
        if (jwt.isNullOrEmpty()) {
            filterChain.doFilter(request, response)
            return
        }
        val userId = jwtService.extractId(jwt)
        if (SecurityContextHolder.getContext().authentication == null) {
            // Update auth context for filters
            val userDetails = userDetailsService.loadUserByUsername(userId)
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
