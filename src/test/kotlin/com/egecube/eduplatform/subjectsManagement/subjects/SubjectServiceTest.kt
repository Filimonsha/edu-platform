package com.egecube.eduplatform.subjectsManagement.subjects

import com.egecube.eduplatform.subjectsManagement.courses.CourseService
import com.egecube.eduplatform.subjectsManagement.participants.ParticipantRepository
import com.egecube.eduplatform.subjectsManagement.participants.domain.Participant
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
internal class SubjectServiceTest {

    @Mock
    lateinit var subjectRepository: SubjectRepository;

    @Mock
    lateinit var participantRepository: ParticipantRepository;

    @Mock
    lateinit var courseService: CourseService;

    @Mock
    lateinit var applicationEventPublisher: ApplicationEventPublisher;

    @InjectMocks
    lateinit var subjectService: SubjectService;

    @Test
    fun addParticipantsToSubjectTestSuccessful() {

        Mockito.`when`(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subject))
        Mockito.`when`(participantRepository.findById(participantId)).thenReturn(Optional.of(participant))

        val result = subjectService.addParticipantsToSubject(subjectId, setOf(participantId))

        assertTrue(result.participants.contains(participant), "Participant added to subject")
    }

    companion object {
        private const val subjectId = 1L
        private val subject: Subject = Subject(subjectId, "Math", "desctiprion")
        private const val participantId = 1L
        private val participant: Participant = Participant(
            participantId, "name", "secondName", "mail", "Admin"
        )
        private val logger = LoggerFactory.getLogger(this::class.java)


        @JvmStatic
        @BeforeAll
        fun prepare(): Unit {
            logger.info("\n Start test Participant service")
        }
    }
}