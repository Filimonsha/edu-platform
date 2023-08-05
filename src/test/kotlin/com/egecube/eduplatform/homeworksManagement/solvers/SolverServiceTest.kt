package com.egecube.eduplatform.homeworksManagement.solvers

import com.egecube.eduplatform._security_.accounts.domain.UserRole
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.HomeworkAnswerRepository
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.HomeworkRepository
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.Evaluate
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.Homework
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.HomeworkAnswer
import com.egecube.eduplatform.homeworksManagement.solvers.dto.HomeworkAnswerPutRequestDTO
import com.egecube.eduplatform.homeworksManagement.solvers.iternal.SolverRepository
import com.egecube.eduplatform.homeworksManagement.solvers.iternal.domain.Solver
import com.egecube.eduplatform.homeworksManagement.tasks.dto.TaskAnswerPutRequestDTO
import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.Task
import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.TaskAnswer
import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.TaskAnswerType
import com.egecube.eduplatform.subjectsManagement.courses.Course
import com.egecube.eduplatform.subjectsManagement.courses.CourseServiceTest
import com.egecube.eduplatform.subjectsManagement.participants.domain.Participant
import com.egecube.eduplatform.subjectsManagement.subjects.Subject
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.slf4j.LoggerFactory
import java.time.ZonedDateTime
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class SolverServiceTest {
    @Mock
    lateinit var homeworkAnswerRepository: HomeworkAnswerRepository

    @Mock
    lateinit var solverRepository: SolverRepository

    @Mock
    lateinit var homeworkRepository: HomeworkRepository

    @InjectMocks
    lateinit var service: SolverService

    @Test
    fun addEvaluateOnHomeworkAnswer() {
        Mockito.`when`(homeworkAnswerRepository.findBy_id(homeworkAnswerId))
            .thenReturn(Optional.of(homeworkAnswer))

        Mockito.`when`(homeworkAnswerRepository.save(homeworkAnswerWithEvaluate))
            .thenReturn(homeworkAnswerWithEvaluate)


        service.addEvaluateOnHomeworkAnswer(
            homeworkAnswerId, HomeworkAnswerPutRequestDTO(
                commonEvaluate,
                listOf(TaskAnswerPutRequestDTO(task1Id, taskAnswerEvaluate))
            )
        )


    }

    companion object {
        private const val solverId = 1L
        private const val homeworkId = "1"
        private const val task1Id = "1"
        private const val homeworkAnswerId = "1"

        private val commonEvaluate = Evaluate(
            Double.MAX_VALUE,
            "Common feedback"
        )
        private val taskAnswerEvaluate = Evaluate(Double.MAX_VALUE, "Feedback on first task")
        private val taskAnswer = TaskAnswer(
            task1Id,
            TaskAnswerType.TaskUserAnswer(
                false,
                TaskAnswerType.TaskRightAnswer.TextAnswer("lala")
            )
        )
        private val tasksAnswerWithEvaluate = taskAnswer.copy(evaluate = taskAnswerEvaluate)

        private val solver: Solver = Solver(solverId, "name", "secondName", "mail", UserRole.ADMIN)
        private val homeworkAnswer = HomeworkAnswer(
            homeworkAnswerId, "1", solver._id?: throw Exception("id не указан"), listOf(
                taskAnswer
            )
        )
        private val homeworkAnswerWithEvaluate = homeworkAnswer.copy(evaluate = commonEvaluate)
        private val homework: Homework = Homework(
            homeworkId,
            "1",
            Long.MAX_VALUE,
            "homework",
            "description",
            ZonedDateTime.now(),
            mutableSetOf(
                Task(
                    task1Id,
                    homeworkId,
                    "task 1",
                    "description",
                    1,
                    TaskAnswerType.TaskRightAnswer.TextAnswer("correct"),
                )
            ),
            mutableListOf(solver._id!!), mutableSetOf(homeworkAnswer._id!!)

        )

        //        private val subject: Subject = Subject(courseId, "Math", "desctiprion")
        private val logger = LoggerFactory.getLogger(this::class.java)


        @JvmStatic
        @BeforeAll
        fun prepare(): Unit {
            logger.info("\n Start test Solver service")

        }
    }
}