package com.egecube.eduplatform.subjectsManagement.courses

import com.egecube.eduplatform.subjectsManagement.participants.ParticipantRepository
import com.egecube.eduplatform.subjectsManagement.participants.domain.Participant
import com.egecube.eduplatform.subjectsManagement.subjects.Subject
import com.egecube.eduplatform.subjectsManagement.subjects.SubjectRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class CourseServiceTest {
    @Mock
    lateinit var courseRepository: CourseRepository

    @Mock
    lateinit var applicationEventPublisher: ApplicationEventPublisher

    @Mock
    lateinit var participantRepository: ParticipantRepository

    @Mock
    lateinit var subjectRepository: SubjectRepository

    @InjectMocks
    lateinit var courseService: CourseService;


    @Test
    fun addParticipantToCourseTest() {
        Mockito.`when`(courseRepository.findById(courseId)).thenReturn(Optional.of(course))
        Mockito.`when`(courseRepository.getAllBySubjectId(subjectId)).thenReturn(listOf(course))

        val result = courseService.addParticipantToCourse(participant, subjectId, courseId)

        assertTrue(result.participants.contains(participant), "Participant added to subject")
    }

    companion object {
        private const val subjectId = 1L
        private const val courseId = 1L
        private const val participantId = 1L

        private val subject: Subject = Subject(courseId, "Math", "desctiprion")
        private val course: Course = Course(courseId, "new course", subject)
        private val participant: Participant = Participant(
            participantId, "name", "secondName", "mail", "Admin"
        )

        private val logger = LoggerFactory.getLogger(this::class.java)


        @JvmStatic
        @BeforeAll
        fun prepare(): Unit {
            logger.info("\n Start test Course service")

        }
    }
}