package com.egecube.eduplatform.lecturesManagement.listeners

import com.egecube.eduplatform.lecturesManagement.lectures.LectureResponseDto
import com.egecube.eduplatform.lecturesManagement.lectures.internal.Lecture
import com.egecube.eduplatform.lecturesManagement.lectures.internal.LecturesRepository
import com.egecube.eduplatform.lecturesManagement.listeners.internal.Listener
import com.egecube.eduplatform.lecturesManagement.listeners.internal.ListenersRepository
import com.egecube.eduplatform.subjectsManagement.events.ParticipantRegistered
import org.modelmapper.ModelMapper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Service
class ListenersService(
    private val listenersRepository: ListenersRepository,
    private val lecturesRepository: LecturesRepository,
    private val modelMapper: ModelMapper,
) {


    fun getListeners(): List<Listener> {
        return listenersRepository.findAll();
    }

    fun getAllListenerLectures(listenerId: Long): List<LectureResponseDto> {
        val foundListener = listenersRepository.findById(listenerId).orElseThrow()
        return foundListener.availableLectures
            .map { lecture: Lecture -> modelMapper.map(lecture, LectureResponseDto::class.java) }
    }

    fun addToListenerAvailableLecture(listenerId: Long, lecturesIds: Set<Long>) {
        val foundListener = listenersRepository.findById(listenerId).orElseThrow()

        lecturesIds.forEach { lectureId ->
            val foundLecture = lecturesRepository.findById(
                lectureId
            ).orElseThrow()


            foundListener.availableLectures.add(foundLecture)
            listenersRepository.save(foundListener)

            foundLecture.listeners.add(foundListener)
            lecturesRepository.save(foundLecture)

        }
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun createListener(participantRegistered: ParticipantRegistered) {

        val createdListener = Listener(
            participantRegistered.participantId,
            participantRegistered.participantName,
            participantRegistered.participantSecondName,
            participantRegistered.participantEmail,
        )
        val savedListener = listenersRepository.save(createdListener)
    }
}