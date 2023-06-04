package com.egecube.eduplatform.subjectsManagement.participants

import com.egecube.eduplatform.subjectsManagement.participants.consts.ParticipantsRoutes
import com.egecube.eduplatform.subjectsManagement.participants.dto.ParticipantRequestDto
import com.egecube.eduplatform.subjectsManagement.participants.dto.ParticipantResponseDto
import com.egecube.eduplatform.subjectsManagement.participants.utils.mapParticipantToResponse
import com.egecube.eduplatform.subjectsManagement.participants.utils.mapRequestToParticipant
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
            .map { participant -> mapParticipantToResponse(participant) }
    }

    @PostMapping(ParticipantsRoutes.PARTICIPANTS)
    fun createParticipant(@RequestBody participantRequestDto: ParticipantRequestDto): ParticipantResponseDto {

        val participant = mapRequestToParticipant(participantRequestDto)
        val createdParticipant = participantService.createParticipant(participant)

        return mapParticipantToResponse(createdParticipant)
    }

    @GetMapping(ParticipantsRoutes.PARTICIPANT)
    fun getParticipantById(@PathVariable id: Long): ParticipantResponseDto {
        return mapParticipantToResponse(participantService.getParticipantById(id).orElseThrow())
    }


}