package com.egecube.eduplatform.lecturesManagement.lectures

import com.egecube.eduplatform.lecturesManagement.lectures.internal.Lecture
import com.egecube.eduplatform.lecturesManagement.lectures.internal.LecturesRepository
import com.egecube.eduplatform.lecturesManagement.listeners.internal.ListenersRepository
import org.springframework.stereotype.Service

@Service
class LecturesService(
    private val lecturesRepository: LecturesRepository,
    private val listenerRepository: ListenersRepository
) {

    fun getById(lectureId: Long): Lecture {
        return lecturesRepository.findById(lectureId).orElseThrow()
    }

    fun createLecture(lecture: Lecture, listenersIds: Set<Long>): Lecture {
        val createdLecture = lecturesRepository.save(lecture)

        listenersIds.forEach { listenerId: Long ->
            val foundListener = listenerRepository.findById(listenerId).orElseThrow()

//            TODO надо ли найдемнному листенеру добавлять нашу лекцию ?
            createdLecture.listeners.add(foundListener)
            listenerRepository.save(foundListener)
        }
        return createdLecture
    }
}