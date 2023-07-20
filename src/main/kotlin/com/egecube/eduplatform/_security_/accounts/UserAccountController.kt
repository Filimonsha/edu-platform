package com.egecube.eduplatform._security_.accounts

import com.egecube.eduplatform._security_.access_rights_utils.AccessRightsService
import com.egecube.eduplatform._security_.accounts.consts.UserAccountRoutes
import com.egecube.eduplatform._security_.accounts.dto.ChangeUserDataDto
import com.egecube.eduplatform._security_.accounts.dto.RegisterRequest
import com.egecube.eduplatform._security_.accounts.dto.UserAccountDto
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserAccountController(
    private val userService: UserAccountService,
    private val accessRightsService: AccessRightsService
) {
    // Open endpoint
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

    // Open endpoint
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

    // Open endpoint
    @PostMapping(UserAccountRoutes.ACCOUNTS)
    fun createAccount(
        @RequestBody newUser: RegisterRequest
    ): ResponseEntity<Long> {
        val registered = userService.registerNewUser(newUser)
        return if (registered != null) {
            ResponseEntity.ok().body(registered)
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    // Owner or superuser
    @PatchMapping(UserAccountRoutes.ACCOUNT)
    fun changeAccountData(
        @PathVariable("id") userId: Long,
        @RequestBody newData: ChangeUserDataDto,
        httpRequest: HttpServletRequest
    ): ResponseEntity<Long> {
        val fromUserOrAdmin = accessRightsService.isJwtForOwnerOrSuperuser(httpRequest, userId)
        val fromUser = accessRightsService.isJwtValidForAccessor(httpRequest, userId)
        return if (!fromUserOrAdmin) {
            ResponseEntity.badRequest().build()
        } else {
            val updated = userService.changeBaseUserDataById(userId, newData)
            if (!fromUser) {
                // Not from user leaves from admin
                userService.changeSecureUserDataById(userId, newData)
            }
            ResponseEntity.ok().body(updated)
        }
    }

    // Owner or superuser
    @DeleteMapping(UserAccountRoutes.ACCOUNT)
    fun deleteAccountById(
        @PathVariable("id") userId: Long,
        httpRequest: HttpServletRequest
    ): ResponseEntity<Long> {
        return if (accessRightsService.isJwtForOwnerOrSuperuser(httpRequest, userId)) {
            userService.deleteAccountById(userId)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.badRequest().build()
        }
    }
}