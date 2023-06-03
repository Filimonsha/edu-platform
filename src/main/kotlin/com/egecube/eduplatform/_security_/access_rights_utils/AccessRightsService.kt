package com.egecube.eduplatform._security_.access_rights_utils

import com.egecube.eduplatform._security_.accounts.UserAccountRepository
import com.egecube.eduplatform._security_.accounts.domain.UserRole
import com.egecube.eduplatform._security_.http_utils.HeaderUtils
import com.egecube.eduplatform._security_.jwt_utils.JwtService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AccessRightsService {

    @Autowired
    private lateinit var headerUtils: HeaderUtils

    @Autowired
    private lateinit var jwtService: JwtService

    @Autowired
    private lateinit var userAccountRepository: UserAccountRepository

    fun extractJwtIfPresentInRequest(req: HttpServletRequest): String? {
        if (!headerUtils.jwtHeaderCorrect(req)) return null
        return headerUtils.extractJwt(req)
    }

    // Check whether jwtToken is destined for accessor or jwt is for superuser
    fun isJwtForOwnerOrSuperuser(jwtContainer: HttpServletRequest, accessorId: Long): Boolean {
        return try {
            val jwt = extractJwtIfPresentInRequest(jwtContainer)
            val jwtUserName = jwtService.extractUserMail(jwt!!)
            val userFromJwt = userAccountRepository.findByEmail(jwtUserName!!)
            val userFromId = userAccountRepository.findById(accessorId).get()

            jwtService.isTokenValid(jwt, userFromId.email) ||
                    !jwtService.isTokenExpired(jwt) &&
                    userFromJwt!!.authorities.first().authority == UserRole.ADMIN.toString()
        } catch (e: UsernameNotFoundException) {
            false
        } catch (e: NullPointerException) {
            false
        }
    }


    // Check whether jwtToken valid just for user
    fun isJwtValidForAccessor(jwtContainer: HttpServletRequest, accessorId: Long): Boolean {
        val jwt = extractJwtIfPresentInRequest(jwtContainer)
        if (jwt.isNullOrEmpty()) return false
        return try {
            val userNameFromId = userAccountRepository.findById(accessorId).get().email
            jwtService.isTokenValid(jwt, userNameFromId)
        } catch (e: NoSuchElementException) {
            return false
        }
    }
}