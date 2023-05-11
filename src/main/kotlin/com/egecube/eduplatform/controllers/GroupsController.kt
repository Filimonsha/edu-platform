package com.egecube.eduplatform.controllers

import com.egecube.eduplatform.flowsManagement.domain.FlowGroup
import com.egecube.eduplatform.flowsManagement.services.GroupService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/flowsManagement/flows")
class GroupsController(private val groupService: GroupService) {

    @GetMapping("/{flowId}/groups")
    fun getGroupsByFlow(@PathVariable(value = "flowId") flowId:Long):List<FlowGroup>{
        val groups = groupService.getAllByFlowId(flowId);
        return groups
    }
}