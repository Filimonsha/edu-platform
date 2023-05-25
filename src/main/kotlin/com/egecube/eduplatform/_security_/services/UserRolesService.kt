package com.egecube.eduplatform._security_.services

import com.egecube.eduplatform._security_.dto.requests.ChangeRoleRequest
import com.egecube.eduplatform._security_.jwt_utils.JwtService
import com.egecube.eduplatform._security_.user_data.UserAccountRepository
import com.egecube.eduplatform._security_.user_data.UserRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserRolesService {
    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Autowired
    private lateinit var userAccountRepository: UserAccountRepository

    @Autowired
    private lateinit var jwtService: JwtService

    fun changeUserRole(request: ChangeRoleRequest, askerJwt: String): Boolean {
        val askerName = jwtService.extractUsername(askerJwt)
            ?: return false
        val askerDetails = userDetailsService.loadUserByUsername(askerName)
        return if (
            jwtService.isTokenValid(askerJwt, askerDetails) &&
            askerDetails.authorities.first().authority == UserRole.ADMIN.toString()
        ) {
            // todo possible change of authorities sets
            val askedUser = userAccountRepository.findByEmail(request.userToChange)
                ?: return false
            askedUser.role = request.newRole
            userAccountRepository.save(askedUser)
            true
        } else {
            false
        }

    }
}