package com.egecube.eduplatform.controllers

import com.egecube.eduplatform.flowsManagement.domain.Flow
import com.egecube.eduplatform.flowsManagement.services.FlowService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/flowsManagement/flows")
class FlowsController(private val flowService: FlowService) {


    @GetMapping("/")
    fun getFlows() = ResponseEntity(flowService.getAll(), HttpStatus.OK)

    @GetMapping("/{id}")
    fun getFlow(@PathVariable(value = "id") flowId: Long): ResponseEntity<Flow> {
        val flow = flowService.get(flowId)
        return if (flow.isPresent)
            ResponseEntity(flow.get(), HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }


}