package com.egecube.eduplatform.classesManagement.controllers

import com.egecube.eduplatform.classesManagement.domain.Participant
import com.egecube.eduplatform.classesManagement.dto.SubjectDto
import com.egecube.eduplatform.classesManagement.services.SubjectService
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/subjectsManagement/subjects")
class SubjectController(
    private val subjectService: SubjectService,
    private val modelMapper: ModelMapper,
) {


    @GetMapping("/")
    fun getFlows() =
        ResponseEntity(
            subjectService.getAll().map { flow -> modelMapper.map(flow, SubjectDto::class.java) },
            HttpStatus.OK
        )

    @GetMapping("/{id}")
    fun getFlowById(@PathVariable(value = "id") flowId: Long): ResponseEntity<SubjectDto> {
        val subject = subjectService.get(flowId).orElseThrow()

        return ResponseEntity(modelMapper.map(subject, SubjectDto::class.java), HttpStatus.OK)
    }


    @PostMapping("/{id}/participants")
    fun addParticipantToSubject(@PathVariable id: Long, @RequestBody participant: Participant) {
        subjectService.addParticipantToSubject(id, participant)
    }

}