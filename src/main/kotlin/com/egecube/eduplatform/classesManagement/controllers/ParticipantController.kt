package com.egecube.eduplatform.classesManagement.controllers

import com.egecube.eduplatform.classesManagement.services.ParticipantService
import org.modelmapper.ModelMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/subjectsManagement/participants")
class ParticipantController(
    private val participantService: ParticipantService,
    private val modelMapper: ModelMapper
) {

    @GetMapping("/")
    fun getAllParticipants(): List<ParticipantResponseDto> {
        return participantService.getAllParticipants()
            .map { participant -> modelMapper.map(participant, ParticipantResponseDto::class.java) }
    }

    @GetMapping("/{id}")
    fun getParticipantById(@PathVariable id: Long): ParticipantResponseDto {
        return modelMapper.map(
            participantService.getParticipantById(id).orElseThrow(),
            ParticipantResponseDto::class.java
        )
    }

    @PostMapping("/")
    fun createParticipant(@RequestBody participantRequestDto: ParticipantRequestDto): ParticipantResponseDto {
        println(participantRequestDto.toString())
        return participantService.createParticipant(participantRequestDto)
    }

}