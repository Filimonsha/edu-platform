package com.egecube.eduplatform.homeworksManagement.solvers

import com.egecube.eduplatform._security_.accounts.domain.UserRole
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.HomeworkAnswerRepository
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.HomeworkRepository
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.Homework
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.HomeworkAnswer
import com.egecube.eduplatform.homeworksManagement.solvers.dto.HomeworkAnswerPutRequestDTO
import com.egecube.eduplatform.homeworksManagement.solvers.iternal.SolverRepository
import com.egecube.eduplatform.homeworksManagement.solvers.iternal.domain.Solver
import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.TaskAnswer
import com.egecube.eduplatform.subjectsManagement.events.ParticipantRegistered
import com.egecube.eduplatform.subjectsManagement.subjects.SubjectService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener


@Service
class SolverService(
    private val solverRepository: SolverRepository,
    private val homeworkAnswerRepository: HomeworkAnswerRepository,
    private val homeworkRepository: HomeworkRepository,
    private val subjectService: SubjectService
) {

    fun getAllSolvers(): MutableList<Solver> {
        return solverRepository.findAll()
    }

    fun getSolverById(solverId: Long): Solver? {
        return solverRepository.findById(solverId).orElseThrow()
    }

    fun getAllSolverHomeworksBySubject(solverId: Long, subjectId: Long): List<Homework> {
        return homeworkRepository.findAllBySubjectIdAndSolversContains(subjectId, mutableListOf(subjectId))
    }

    fun addAnswerOnHomework(solverId: Long, homeworkId: String, tasksAnswer: List<TaskAnswer>): HomeworkAnswer {
        val foundSolver = solverRepository.findBy_id(solverId).orElseThrow()
        val homeworkAnswer = HomeworkAnswer(
            null,
            homeworkId,
            foundSolver._id ?: throw Exception("Id yе определен"),
            tasksAnswer
        )
        val homework = homeworkRepository.findById(homeworkAnswer.homeworkId).orElseThrow()
        val savedHomeworkAnswer = homeworkAnswerRepository.save(homeworkAnswer)
        //            TODO добавить кастомный эксепшен на не соотвествие размера тасок и ответов
        homework.tasks.size != homeworkAnswer.answers.size && throw Exception("Ne sovpadaet dlina )")
        homeworkAnswer.answers.forEach { taskAnswer: TaskAnswer ->
//            TODO добавить кастомный эксепшен на не соотвествие инутых тасок к существующей домашки
            val notFoundIds = homework.tasks.map { it._id } - homeworkAnswer.answers.map { it.taskId }.distinct()
            if (notFoundIds.isNotEmpty()) {
                throw Exception("Not found task ids: ${notFoundIds.joinToString()}")
            }
        }
        homework.answers.add(savedHomeworkAnswer._id!!)
        homeworkRepository.save(homework)
        return savedHomeworkAnswer
    }

    //    @Secured("TEACHER")
    fun addEvaluateOnHomeworkAnswer(
        answerId: String,
        homeworkAnswerPutRequestDTO: HomeworkAnswerPutRequestDTO
    ) {
        val foundHomeworkAnswer = homeworkAnswerRepository.findBy_id(answerId).orElseThrow()

//        TODO сделать проверку на то , чтоб оценку можно было дать только расширенному заданию
        foundHomeworkAnswer.evaluate = homeworkAnswerPutRequestDTO.evaluate
        foundHomeworkAnswer.answers.forEach { taskAnswer -> println(taskAnswer.taskAnswer.answer::class.java) }

        val answersMap = foundHomeworkAnswer.answers.associateBy { it.taskId }.toMutableMap()
        homeworkAnswerPutRequestDTO.tasksAnswer.forEach { taskAnswer ->
            val taskAnswer1 = answersMap[taskAnswer.taskId] ?: throw Exception("Такого задания нет")
            taskAnswer1.evaluate = taskAnswer.evaluate
            answersMap[taskAnswer.taskId] = taskAnswer1
        }
        foundHomeworkAnswer.answers = answersMap.values.toList()

        homeworkAnswerRepository.save(foundHomeworkAnswer)
    }

    fun getAllSolverHomeworksAnswers(solverId: Long): List<HomeworkAnswer> {
        val foundSolver = solverRepository.findBy_id(solverId).orElseThrow()
        return homeworkAnswerRepository.findAllBySolverId(foundSolver._id ?: throw Exception("Id yе определен"))
    }


    @Async
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun createSolver(
        participantRegistered: ParticipantRegistered
    ) {
        solverRepository.save(
            Solver(
                participantRegistered.participantId,
                participantRegistered.participantName,
                participantRegistered.participantSecondName,
                participantRegistered.participantEmail,
//                TODO
                UserRole.TEACHER
            )
        )
    }
}