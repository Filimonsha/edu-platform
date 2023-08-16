package com.egecube.eduplatform.subjectsManagement.participants

import com.egecube.eduplatform._security_.events.UserAccountCreated
import com.egecube.eduplatform.subjectsManagement.events.ParticipantRegistered
import com.egecube.eduplatform.subjectsManagement.participants.domain.Participant
import org.springframework.context.ApplicationEventPublisher
import org.springframework.modulith.ApplicationModuleListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ParticipantService(
    private val participantRepository: ParticipantRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun getAllParticipants(): List<Participant> {
        return participantRepository.findAll()
    }

    fun getParticipantById(participantId: Long) = participantRepository.findById(participantId)

    @ApplicationModuleListener
    fun registerParticipantFromUser(userAccountCreated: UserAccountCreated){
        val (userId, userMail, userPhone,email, lastName) = userAccountCreated.data
        createParticipant(
            Participant(
                id = userAccountCreated.data.userId,
                name = userMail,
                secondName = lastName,
                email = email,
                participantRole = lastName,
            )
        )
    }

    @Transactional
    fun createParticipant(participant: Participant): Participant {

        val savedParticipant = participantRepository.save(participant)

        applicationEventPublisher.publishEvent(
            ParticipantRegistered(
                savedParticipant.id,
                savedParticipant.name,
                savedParticipant.secondName,
                savedParticipant.email
            )
        )

        return savedParticipant
    }

}