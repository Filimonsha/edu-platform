package com.egecube.eduplatform.homeworksManagement.homeworks

import com.egecube.eduplatform.homeworksManagement.homeworks.dto.HomeworkRequestDto
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.HomeworkAnswerRepository
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.HomeworkRepository
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.Homework
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.HomeworkAnswer
import com.egecube.eduplatform.homeworksManagement.solvers.iternal.SolverRepository
import com.egecube.eduplatform.homeworksManagement.tasks.dto.TaskRequestDTO
import com.egecube.eduplatform.homeworksManagement.tasks.internal.TaskRepository
import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.Task
import com.egecube.eduplatform.subjectsManagement.subjects.SubjectService
import org.springframework.stereotype.Service

@Service
class HomeworkService(
    private val homeworkRepository: HomeworkRepository,
    private val solverRepository: SolverRepository,
    private val taskRepository: TaskRepository,
    private val homeworkAnswerRepository: HomeworkAnswerRepository,
    private val subjectService: SubjectService
) {
    fun getAllHomeworks(): List<Homework> {
        return homeworkRepository.findAll()
    }

    fun getAllHomeworksByCreatorId(creatorId: String): List<Homework> {
        return homeworkRepository.findAllByCreatorId(creatorId)
    }

    fun getAllHomeworksBySolverId(solverId: Long): List<Homework> {
        val solver = solverRepository.findBy_id(solverId).orElseThrow()
        return solver.homeworks.map { homeworkId -> homeworkRepository.findBy_id(homeworkId).orElseThrow() }
    }

    fun createHomework(homeworkRequestDto: HomeworkRequestDto): Homework {
        val (title, subjectId, creatorId, description, deadline, solversIds) = homeworkRequestDto
        val foundSubject = subjectService.getById(subjectId)
        val homework = Homework(
            null,
            creatorId,
            foundSubject?.id ?: throw Exception("id не задано"),
            title,
            description,
            deadline,
        )


        homework.solvers.addAll(solversIds)

        val savedHomework = homeworkRepository.save(homework)
        solversIds.forEach { solverId ->
            val foundSolver = solverRepository.findBy_id(solverId).orElseThrow()
            foundSolver.homeworks.add(savedHomework._id ?: throw Exception("Id не задан"))
            solverRepository.save(foundSolver)
        }
        return savedHomework
    }

    fun getHomework(homeworkId: String): Homework {
        return homeworkRepository.findById(homeworkId).orElseThrow()
    }

    fun addTasksToHomework(homeworkId: String, dtoTasks: List<TaskRequestDTO>): Homework {
        val foundHomework = homeworkRepository.findById(homeworkId).orElseThrow()
//            TODO Как проверять уникальность приритета ?
        //        val priorityRepeats = dtoTasks.associateBy{it.priority}
//            priorityRepeats.
//            .filter{it. .values.size > 1}

        val distinctTasks = dtoTasks.map {
            Task(
                null,
                homeworkId,
                it.title,
                it.description,
                it.priority,
                it.answerVariants,
                it.correctAnswer,
            )
        }.distinctBy { it.priority }


        val savedTasks = distinctTasks.map { task: Task -> taskRepository.save(task) }
        foundHomework.tasks.plusAssign(savedTasks.sortedBy { it.priority })


//        return
        return homeworkRepository.save(foundHomework)
    }

    fun getHomeworkTasks(homeworkId: String): List<Task> {
        return taskRepository.findAllByHomeworkId(homeworkId)
    }

    fun getAllHomeworkAnswers(homeworkId: String): List<HomeworkAnswer> {
        return homeworkAnswerRepository.findAllByHomeworkId(homeworkId)
    }
}