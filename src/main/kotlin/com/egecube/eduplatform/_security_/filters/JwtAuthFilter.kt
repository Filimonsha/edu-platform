package com.egecube.eduplatform._security_.filters


import com.egecube.eduplatform._security_.access_rights_utils.AccessRightsService
import com.egecube.eduplatform._security_.jwt_utils.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val accessRightsService: AccessRightsService,
    private val jwtService: JwtService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        println("filtering for access")
        try {
            val jwt = accessRightsService.extractJwtIfPresentInRequest(request)!!
            val userMail = jwtService.extractUserMail(jwt)
            if (SecurityContextHolder.getContext().authentication == null) {
                // Update auth context for filters
                if (
                    jwtService.isTokenValid(jwt, userMail!!) &&
                    jwtService.extractClaim(jwt, "type") == "access"
                    ) {
                    val auth = UsernamePasswordAuthenticationToken(
                        userMail,
                        jwtService.extractClaim(jwt, "userId"), // get id from db if not authenticated
                        arrayListOf(SimpleGrantedAuthority(
                            jwtService.extractClaim(jwt, "userRights")
                        ))
                    )
                    auth.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = auth
                }
            }
            filterChain.doFilter(request, response)
        } catch (e: NullPointerException) {
            filterChain.doFilter(request, response)
            return
        }
    }
}
