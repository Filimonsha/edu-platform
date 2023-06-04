package com.egecube.eduplatform.subjectsManagement.participants

import com.egecube.eduplatform.subjectsManagement.participants.domain.Participant
import org.springframework.stereotype.Service

@Service
class ParticipantService(
    private val participantRepository: ParticipantRepository,
) {

    fun getAllParticipants(): List<Participant> {
        return participantRepository.findAll()
    }

    fun getParticipantById(participantId: Long) = participantRepository.findById(participantId)
    fun createParticipant(participant: Participant): Participant {

        return participantRepository.save(participant)
    }

}