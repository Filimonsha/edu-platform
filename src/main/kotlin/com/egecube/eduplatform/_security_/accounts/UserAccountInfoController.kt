package com.egecube.eduplatform._security_.accounts

import com.egecube.eduplatform._security_.access_rights_utils.AccessRightsService
import com.egecube.eduplatform._security_.accounts.consts.UserAccountRoutes
import com.egecube.eduplatform._security_.accounts.dto.UserAccountDto
import com.egecube.eduplatform._security_.jwt_utils.JwtService
import io.swagger.v3.oas.annotations.Operation
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserAccountInfoController(
    private val userService: UserAccountService,
    private val accessRightsService: AccessRightsService,
    private val jwtService: JwtService
) {
    @Operation(summary = "Get account info, searching in the repo by unique name (mail)")
    @GetMapping(UserAccountRoutes.ACCOUNTS)
    fun getAccountInfoByName(
        @RequestParam("name") name: String
    ): ResponseEntity<UserAccountDto> {
        val userInfo = userService.getUserByName(name)
        return if (userInfo != null) {
            ResponseEntity.ok().body(userInfo)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @Operation(summary = "Get account info by id")
    @GetMapping(UserAccountRoutes.ACCOUNT)
    fun getAccountInfoById(
        @PathVariable("id") userId: Long
    ): ResponseEntity<UserAccountDto> {
        val userInfo = userService.getUserById(userId)
        return if (userInfo != null) {
            ResponseEntity.ok().body(userInfo)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @Operation(summary = "Get account info from jwt")
    @GetMapping(UserAccountRoutes.ME_ACC)
    fun getAccountInfoByJwt(
        request: HttpServletRequest
    ): ResponseEntity<UserAccountDto> {
        return try {
            val jwt = accessRightsService.extractJwtIfPresentInRequest(request)
            val userMail = jwtService.extractUserMail(jwt!!)
            val userInfo = userService.getUserByName(userMail!!)
            ResponseEntity.ok().body(userInfo!!)
        } catch (_: NullPointerException) {
            ResponseEntity.badRequest().build()
        }

    }
}