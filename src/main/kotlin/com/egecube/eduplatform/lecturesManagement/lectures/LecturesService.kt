package com.egecube.eduplatform.lecturesManagement.lectures

import com.egecube.eduplatform.lecturesManagement.lectures.internal.Lecture
import com.egecube.eduplatform.lecturesManagement.lectures.internal.LectureRepository
import com.egecube.eduplatform.lecturesManagement.listeners.internal.ListenersRepository
import org.springframework.stereotype.Service

@Service
class LecturesService(
    private val lectureRepository: LectureRepository,
    private val listenerRepository: ListenersRepository
) {

    fun getById(lectureId: Long): Lecture {
        return lectureRepository.findById(lectureId).orElseThrow()
    }

    fun createLecture(lecture: Lecture, listenersIds: Set<Long>): Lecture {
        val createdLecture = lectureRepository.save(lecture)

        listenersIds.forEach { listenerId: Long ->
            val foundListener = listenerRepository.findById(listenerId).orElseThrow()

//            TODO надо ли найдемнному листенеру добавлять нашу лекцию ?
            createdLecture.listeners.add(foundListener)
            listenerRepository.save(foundListener)
        }
        return createdLecture
    }

    fun replaceLecture(lectureId: Long, lectureForReplacement: Lecture): Lecture {
        val foundLecture = lectureRepository
            .findById(lectureId)
            .orElseThrow()

        with(foundLecture) {
            name = lectureForReplacement.name
            description = lectureForReplacement.description
            startsAt = lectureForReplacement.startsAt
            endsAt = lectureForReplacement.endsAt
            flowSrc = lectureForReplacement.flowSrc
        }

        lectureRepository.save(foundLecture)
        return foundLecture
    }
}