package com.egecube.eduplatform.subjectsManagement.subjects

import com.egecube.eduplatform.subjectsManagement.courses.CourseService
import com.egecube.eduplatform.subjectsManagement.events.AddedParticipantToSubject
import com.egecube.eduplatform.subjectsManagement.participants.ParticipantRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.util.*

@Service
class SubjectService(
    private val subjectRepository: SubjectRepository,
    private val participantRepository: ParticipantRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val courseService: CourseService
) {

    fun getById(id: Long): Subject {
        return subjectRepository.findById(id).orElseThrow()
    }

    fun getAll() = subjectRepository.findAll()

    fun addParticipantsToSubject(subjectId: Long, participantsIds: Set<Long>): Subject {
        val subject = subjectRepository.findById(subjectId).orElseThrow()


        participantsIds.forEach { participantId ->
            val foundParticipant = participantRepository.findById(
                participantId
            ).orElseThrow()


            subject.participants.add(foundParticipant);
            subjectRepository.save(subject)

            foundParticipant.relatedSubjects.add(subject);
            participantRepository.save(foundParticipant);

            courseService.addParticipantToCourse(foundParticipant, subjectId, null)

            applicationEventPublisher.publishEvent(AddedParticipantToSubject(foundParticipant.id))

        }


        return subject
    }


}