package com.egecube.eduplatform._security_.auth_controllers

import com.egecube.eduplatform._security_.dto.requests.ChangeRoleRequest
import com.egecube.eduplatform._security_.http_utils.HeaderUtils
import com.egecube.eduplatform._security_.services.UserRolesService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("api/users/roles")
class RolesController {

    @Autowired
    private lateinit var userRolesService: UserRolesService

    @Autowired
    private lateinit var headerUtils: HeaderUtils

    @PostMapping
    fun changeUserRole(
        @RequestBody request: ChangeRoleRequest,
        httpRequest: HttpServletRequest
    ) {
        val userAskingJwt = headerUtils.extractJwt(httpRequest)
        userRolesService.changeUserRole(request, userAskingJwt)
    }

}