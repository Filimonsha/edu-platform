package com.egecube.eduplatform._security_.tokens

import com.egecube.eduplatform._security_.access_rights_utils.AccessRightsService
import com.egecube.eduplatform._security_.accounts.domain.UserAccount
import com.egecube.eduplatform._security_.accounts.domain.UserRole
import com.egecube.eduplatform._security_.jwt_utils.JwtRepository
import com.egecube.eduplatform._security_.jwt_utils.JwtService
import com.egecube.eduplatform._security_.tokens.domain.UserToken
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TokensService(
    private val jwtService: JwtService,
    private val tokenRepository: JwtRepository,
    private val accessRightsService: AccessRightsService
) {

    @Transactional
    fun giveOutNewRefreshForUser(email: String, id: Long, role: UserRole): String {
        tokenRepository.deleteAllByUsername(email)
        val newToken = jwtService.generateRefreshToken(email, id, role)
        val registry = UserToken.build().also {
            it.token = newToken
            it.username = email
        }
        tokenRepository.save(registry)
        return newToken
    }

    @Transactional
    fun giveOutNewRefreshForUser(account: UserAccount): String {
        return giveOutNewRefreshForUser(account.email, account.id, account.role)
    }

    fun checkIfRefreshValidForUser(request: HttpServletRequest): Boolean {
        val refToken = accessRightsService.extractJwtIfPresentInRequest(request) ?: return false
        val userName = jwtService.extractUserMail(refToken)
        val type = jwtService.extractClaim(refToken, "type")
        if (type != "refresh") return false
        if (tokenRepository.findAllByUsername(userName!!).size != 1) return false
        if (tokenRepository.findByUsername(userName).get().token != refToken) return false
        return jwtService.isTokenValid(refToken, userName)
    }
}