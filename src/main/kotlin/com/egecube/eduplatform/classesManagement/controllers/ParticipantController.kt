package com.egecube.eduplatform.classesManagement.controllers

import com.egecube.eduplatform.classesManagement.dto.ParticipantRequestDto
import com.egecube.eduplatform.classesManagement.dto.ParticipantResponseDto
import com.egecube.eduplatform.classesManagement.routes.ParticipantsRoutes
import com.egecube.eduplatform.classesManagement.services.ParticipantService
import io.swagger.v3.oas.annotations.tags.Tag
import org.modelmapper.ModelMapper
import org.springframework.web.bind.annotation.*

@RestController
@Tag(
    name = "Participants",
    description = "The subjects participants API"
)
class ParticipantController(
    private val participantService: ParticipantService,
    private val modelMapper: ModelMapper
) {


    @GetMapping(ParticipantsRoutes.PARTICIPANTS)
    fun getAllParticipants(): List<ParticipantResponseDto> {
        return participantService.getAllParticipants()
            .map { participant -> modelMapper.map(participant, ParticipantResponseDto::class.java) }
    }

    @PostMapping(ParticipantsRoutes.PARTICIPANTS)
    fun createParticipant(@RequestBody participantRequestDto: ParticipantRequestDto): ParticipantResponseDto {
        println(participantRequestDto.toString())

        val createdParticipant = participantService.createParticipant(participantRequestDto)
        println(createdParticipant)
        return createdParticipant
    }

    @GetMapping(ParticipantsRoutes.PARTICIPANT)
    fun getParticipantById(@PathVariable id: Long): ParticipantResponseDto {
        return modelMapper.map(
            participantService.getParticipantById(id).orElseThrow(),
            ParticipantResponseDto::class.java
        )
    }


}