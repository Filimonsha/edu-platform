package com.egecube.eduplatform._security_.accounts

import com.egecube.eduplatform._security_.access_rights_utils.AccessRightsService
import com.egecube.eduplatform._security_.accounts.consts.UserAccountRoutes
import com.egecube.eduplatform._security_.accounts.dto.ChangeUserDataDto
import com.egecube.eduplatform._security_.accounts.dto.RegisterRequest
import com.egecube.eduplatform._security_.accounts.dto.UserAccountDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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

    // Open endpoint
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

    // Open endpoint
    @Operation(summary = "Request for creating new user account, back validation")
    @ApiResponses(value = [
        ApiResponse(responseCode = "400", description = "Bad request, user already exists"),
        ApiResponse(responseCode = "200", description = "Account created, user returned")
    ])
    @PostMapping(UserAccountRoutes.ACCOUNTS)
    fun createAccount(
        @RequestBody newUser: RegisterRequest
    ): ResponseEntity<UserAccountDto> {
        val registered = userService.registerNewUser(newUser)
        return if (registered != null) {
            ResponseEntity.ok().body(registered)
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    // Owner or superuser
    @PatchMapping(UserAccountRoutes.ACCOUNT)
    @Operation(summary = "Change user data and rights if changer has higher level")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Changed rights in correlation of own, changed info")
    ])
    fun changeAccountData(
        @PathVariable("id") userId: Long,
        @RequestBody newData: ChangeUserDataDto,
        httpRequest: HttpServletRequest
    ): ResponseEntity<UserAccountDto> {
        val fromUserOrAdmin = accessRightsService.isJwtForOwnerOrSuperuser(httpRequest, userId)
        val fromUser = accessRightsService.isJwtValidForAccessor(httpRequest, userId)
        return if (!fromUserOrAdmin) {
            ResponseEntity.badRequest().build()
        } else {
            var updated = userService.changeBaseUserDataById(userId, newData)
            if (!fromUser) {
                // Not from user leaves from admin
                updated = userService.changeSecureUserDataById(userId, newData)
            }
            ResponseEntity.ok().body(updated)
        }
    }

    // Owner or superuser
    @DeleteMapping(UserAccountRoutes.ACCOUNT)
    @Operation(summary = "Delete account by id, if owner of account or superuser")
    fun deleteAccountById(
        @PathVariable("id") userId: Long,
        httpRequest: HttpServletRequest,
    ): ResponseEntity<Long> {
        return if (accessRightsService.isJwtForOwnerOrSuperuser(httpRequest, userId)) {
            userService.deleteAccountById(userId)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.badRequest().build()
        }
    }
}