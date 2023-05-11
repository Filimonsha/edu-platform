package com.egecube.eduplatform.controllers

import com.egecube.eduplatform.flowsManagement.dto.FlowGroupdDto
import com.egecube.eduplatform.flowsManagement.services.GroupService
import org.modelmapper.ModelMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/flowsManagement/flows")
class GroupsController(private val groupService: GroupService, private val modelMapper: ModelMapper) {

    @GetMapping("/{flowId}/groups/{groupId}")
    fun getGroupById(
        @PathVariable(value = "flowId") flowId: Long,
        @PathVariable(value = "groupId") groupId: Long
    ): FlowGroupdDto {
        return modelMapper.map(groupService.getByFlowIdAndId(flowId, groupId).orElseThrow(), FlowGroupdDto::class.java)
    }

    @GetMapping("/{flowId}/groups")
    fun getGroupsByFlow(@PathVariable(value = "flowId") flowId: Long): List<FlowGroupdDto> {
        return groupService.getAllByFlowId(flowId).map { x -> modelMapper.map(x, FlowGroupdDto::class.java) }
    }
}