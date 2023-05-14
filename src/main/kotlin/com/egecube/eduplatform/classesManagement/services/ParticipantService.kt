package com.egecube.eduplatform.classesManagement.services

import com.egecube.eduplatform.classesManagement.controllers.ParticipantRequestDto
import com.egecube.eduplatform.classesManagement.controllers.ParticipantResponseDto
import com.egecube.eduplatform.classesManagement.domain.Participant
import com.egecube.eduplatform.classesManagement.repositories.ParticipantRepository
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import net.minidev.json.JSONObject
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class ParticipantService(
    private val participantRepository: ParticipantRepository,
    private val modelMapper: ModelMapper
) {

    fun getAllParticipants(): List<Participant> {
        return participantRepository.findAll()
    }

    fun getParticipantById(participantId: Long) = participantRepository.findById(participantId)
    fun createParticipant(participantRequestDto: ParticipantRequestDto): ParticipantResponseDto {

        val newParticipant = modelMapper.map(participantRequestDto, Participant::class.java)
        println(newParticipant.participantRole)
        println(newParticipant.name)
        val savedParticipant = participantRepository.save(newParticipant)

        return modelMapper.map(savedParticipant, ParticipantResponseDto::class.java)
    }

}