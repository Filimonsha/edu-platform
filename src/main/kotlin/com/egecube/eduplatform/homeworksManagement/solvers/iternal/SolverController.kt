package com.egecube.eduplatform.homeworksManagement.solvers.iternal

import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.Homework
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.HomeworkAnswer
import com.egecube.eduplatform.homeworksManagement.solvers.SolverService
import com.egecube.eduplatform.homeworksManagement.solvers.dto.HomeworkAnswerPutRequestDTO
import com.egecube.eduplatform.homeworksManagement.solvers.dto.HomeworkAnswerRequestDTO
import com.egecube.eduplatform.homeworksManagement.solvers.iternal.const.SolversRoute
import com.egecube.eduplatform.homeworksManagement.solvers.iternal.domain.Solver
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class SolverController(
    private val solverService: SolverService
) {

    @GetMapping(SolversRoute.SOLVERS)
    fun readSolvers(): MutableList<Solver> {
        return solverService.getAllSolvers()
    }

    @GetMapping(SolversRoute.SOLVER)
    fun readSolverById(@PathVariable solverId: Long): Solver? {
        return solverService.getSolverById(solverId)
    }


    @GetMapping(SolversRoute.SOLVER_HOMEWORKS)
    @Operation(summary = "Полученине домашек по предмету пользователя")
    fun readAllSolverHomeworksBySubject(@PathVariable solverId: Long, @RequestParam subjectId: Long): List<Homework> {
        return solverService.getAllSolverHomeworksBySubject(solverId, subjectId)
    }



    @GetMapping(SolversRoute.SOLVER_ANSWERS)
    @Operation(summary = "Получение всех ответов пользователя ")
    fun readAllSolverHomeworkAnswers(@PathVariable solverId: Long): List<HomeworkAnswer> {
        return solverService.getAllSolverHomeworksAnswers(solverId)
    }

    @PostMapping(SolversRoute.SOLVER_ANSWERS)
    @Operation(summary = "Создание ответа на домашнее задание")
    fun createHomeworkAnswer(
        @PathVariable solverId: Long,
        @RequestBody homeworkAnswerRequestDTO: HomeworkAnswerRequestDTO
    ): HomeworkAnswer {
        return solverService.addAnswerOnHomework(
            solverId, homeworkAnswerRequestDTO.homeworkId, homeworkAnswerRequestDTO.tasksAnswers
        )
    }

    @PutMapping(SolversRoute.SOLVER_ANSWER)
    fun updateHomeworkAnswerEvaluate(
        @PathVariable solverId: Long,
        @PathVariable answerId: String,
        @RequestBody homeworkAnswerPutRequestDTO: HomeworkAnswerPutRequestDTO
    ) {
        return solverService.addEvaluateOnHomeworkAnswer(
//            solverId, homeworkId,
            answerId,
            homeworkAnswerPutRequestDTO
        )
    }

}