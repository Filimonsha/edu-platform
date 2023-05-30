package com.egecube.eduplatform.subjectsManagement.courses

import com.egecube.eduplatform.subjectsManagement.participants.ParticipantRepository
import com.egecube.eduplatform.subjectsManagement.participants.domain.Participant
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

const val MAX_COURSE_PARTICIPANTS_SIZE = 30

@Service
class CourseService(
    private val courseRepository: CourseRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val participantRepository: ParticipantRepository
) {

    fun getCourseById(id: Long) = courseRepository.findById(id)

    fun getAllCourses() = courseRepository.findAll()

    fun addParticipantToCourse(participant: Participant, courseId: Long?) {
        if (courseId === null) {
            val foundCourses = courseRepository.findAll()
            for (course in foundCourses) {
                if (course.participants.size <= MAX_COURSE_PARTICIPANTS_SIZE) {
                    course.participants.add(participant)
                    courseRepository.save(course)

                    participant.relatedCourses.add(course)
                    participantRepository.save(participant)

                    break
                }
            }
        }
    }


}