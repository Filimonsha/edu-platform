package com.egecube.eduplatform.subjectsManagement.courses

import com.egecube.eduplatform.subjectsManagement.participants.ParticipantRepository
import com.egecube.eduplatform.subjectsManagement.participants.domain.Participant
import com.egecube.eduplatform.subjectsManagement.subjects.SubjectRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.util.*

const val MAX_COURSE_PARTICIPANTS_SIZE = 30

@Service
class CourseService(
    private val courseRepository: CourseRepository,
    private val subjectRepository: SubjectRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val participantRepository: ParticipantRepository
) {

    fun getCourseById(subjectId: Long, courseId: Long): Optional<Course> =
        courseRepository.getBySubjectIdAndId(subjectId, courseId)

    fun getCoursesBySubjectId(subjectId: Long): List<Course> = courseRepository.getAllBySubjectId(subjectId);

    fun createCourse(subjectId: Long, courseName: String): Course {
        val foundSubject = subjectRepository.findById(subjectId).orElseThrow()
        val newCourse = Course(id = null, name = courseName, subject = foundSubject)
        println("COURSE")
        println(newCourse)
        return courseRepository.save(newCourse)
    }

    fun addParticipantToCourse(participant: Participant, subjectId: Long, courseId: Long?): Course {
        val foundCourses = courseRepository.getAllBySubjectId(subjectId);


        if (courseId === null) {
            val courseWithFreePlace =
                foundCourses.find { course: Course -> course.participants.size <= MAX_COURSE_PARTICIPANTS_SIZE }

            return if (courseWithFreePlace !== null) {
                courseWithFreePlace.participants.add(participant)
                courseRepository.save(courseWithFreePlace)

                participant.relatedCourses.add(courseWithFreePlace)
                participantRepository.save(participant)
                courseWithFreePlace
            } else {
                //                TODO продумать создание новых групп ученико, при заполнении оставльных
                courseRepository.findById(1).orElseThrow()
            }
        } else {
//            TODO обработать ситуация, когда в груцппе нет мест
            val foundCourse = courseRepository.findById(courseId).orElseThrow()
            foundCourse.participants.add(participant)
            courseRepository.save(foundCourse);
            return foundCourse
        }

    }


}