package com.egecube.eduplatform._security_.access_rights_utils

import com.egecube.eduplatform._security_.accounts.domain.UserRole
import com.egecube.eduplatform._security_.http_utils.HeaderUtils
import com.egecube.eduplatform._security_.jwt_utils.JwtService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AccessRightsService {

    @Autowired
    private lateinit var headerUtils: HeaderUtils

    @Autowired
    private lateinit var jwtService: JwtService

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    fun extractJwtIfPresentInRequest(req: HttpServletRequest): String? {
        if (!headerUtils.jwtHeaderCorrect(req)) return null
        return headerUtils.extractJwt(req)
    }

    // Check whether jwtToken is destined for accessor or jwt is for superuser
    fun isJwtForOwnerOrSuperuser(jwtContainer: HttpServletRequest, accessorId: Long): Boolean {
        val jwt = extractJwtIfPresentInRequest(jwtContainer)
        if (jwt.isNullOrEmpty()) return false
        val userId = jwtService.extractId(jwt)
        if (userId.isNullOrEmpty()) return false
        return try {
            // Actually loads by id, replaced logic
            val userDetails = userDetailsService.loadUserByUsername(userId)
            // If for user or non expired admin
            jwtService.isTokenValid(jwt, userDetails) ||
                    !jwtService.isTokenExpired(jwt) &&
                    userDetails.authorities.first().authority == UserRole.ADMIN.toString()
        } catch (e: UsernameNotFoundException) {
            false
        }
    }


    // Check whether jwtToken valid just for user
    fun isJwtValidForUser(jwtContainer: HttpServletRequest, accessorId: Long): Boolean {
        val jwt = extractJwtIfPresentInRequest(jwtContainer)
        if (jwt.isNullOrEmpty()) return false
        val userId = jwtService.extractId(jwt)
        if (userId.isNullOrEmpty()) return false
        return try {
            // Actually loads by id, replaced logic
            val userDetails = userDetailsService.loadUserByUsername(userId)
            jwtService.isTokenValid(jwt, userDetails)
        } catch (e: UsernameNotFoundException) {
            false
        }
    }
}