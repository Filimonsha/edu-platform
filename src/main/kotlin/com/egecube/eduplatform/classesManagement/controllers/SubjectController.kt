package com.egecube.eduplatform.classesManagement.controllers

import com.egecube.eduplatform.classesManagement.dto.SubjectDto
import com.egecube.eduplatform.classesManagement.services.SubjectService
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/flowsManagement/flows")
class SubjectController(private val subjectService: SubjectService, private val modelMapper: ModelMapper) {


    @GetMapping("/")
    fun getFlows() =
        ResponseEntity(subjectService.getAll().map { flow -> modelMapper.map(flow, SubjectDto::class.java) }, HttpStatus.OK)

    @GetMapping("/{id}")
    fun getFlow(@PathVariable(value = "id") flowId: Long): ResponseEntity<SubjectDto> {
        val subject = subjectService.get(flowId).orElseThrow()

        return ResponseEntity(modelMapper.map(subject, SubjectDto::class.java), HttpStatus.OK)
    }


}