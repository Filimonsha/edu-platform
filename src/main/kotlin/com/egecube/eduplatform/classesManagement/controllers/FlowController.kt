package com.egecube.eduplatform.classesManagement.controllers

import com.egecube.eduplatform.classesManagement.dto.FlowDto
import com.egecube.eduplatform.classesManagement.services.FlowService
import org.modelmapper.ModelMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/flowsManagement/flows")
class FlowController(private val flowService: FlowService, private val modelMapper: ModelMapper) {

    @GetMapping("/{flowId}/groups/{groupId}")
    fun getGroupById(
        @PathVariable(value = "flowId") flowId: Long,
        @PathVariable(value = "groupId") groupId: Long
    ): FlowDto {
        return modelMapper.map(flowService.getByFlowIdAndId(flowId, groupId).orElseThrow(), FlowDto::class.java)
    }

    @GetMapping("/{flowId}/groups")
    fun getGroupsByFlow(@PathVariable(value = "flowId") flowId: Long): List<FlowDto> {
        return flowService.getAllByFlowId(flowId).map { x -> modelMapper.map(x, FlowDto::class.java) }
    }
}