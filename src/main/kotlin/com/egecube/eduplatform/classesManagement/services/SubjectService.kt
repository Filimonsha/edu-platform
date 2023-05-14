package com.egecube.eduplatform.classesManagement.services

import com.egecube.eduplatform.classesManagement.domain.Participant
import com.egecube.eduplatform.classesManagement.domain.Subject
import com.egecube.eduplatform.classesManagement.repositories.ParticipantRepository
import com.egecube.eduplatform.classesManagement.repositories.SubjectRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SubjectService(private val subjectRepository: SubjectRepository,private val participantRepository: ParticipantRepository) {
    fun get(id: Long): Optional<Subject> =
        subjectRepository.findById(id)

    fun getAll() = subjectRepository.findAll()

    fun addParticipantToSubject(subjectId: Long, participant: Participant) {
        val subject = subjectRepository.findById(subjectId).orElseThrow()
//        val participant = participantRepository.findById(participantId).orElseThrow()
        subject.participants.orEmpty().plus(participant)
    }
}