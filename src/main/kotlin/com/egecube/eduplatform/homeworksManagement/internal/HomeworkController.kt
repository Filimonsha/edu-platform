package com.egecube.eduplatform.homeworksManagement.internal

import com.egecube.eduplatform.homeworksManagement.HomeworkService
import com.egecube.eduplatform.homeworksManagement.dto.HomeworkAnswerRequestDTO
import com.egecube.eduplatform.homeworksManagement.dto.HomeworkRequestDto
import com.egecube.eduplatform.homeworksManagement.internal.domain.*
import com.egecube.eduplatform.homeworksManagement.internal.routes.HomeworksRoute
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeworkController(
    private val homeworkService: HomeworkService,
) {
    @GetMapping(HomeworksRoute.HOMEWORKS)
    fun getAllHomeworks(): List<HomeWork> {
        return listOf(
            HomeWork(
                " title",
                "description",
//                deadline,
//                setOf()
            setOf(Task("a","",1,TaskAnswerType.TaskRightAnswer.TextAnswer("dfddd")))
            )
        )
//        return homeworkService.getAllHomeworks()
    }

    @PostMapping(HomeworksRoute.HOMEWORKS)
    fun createHomework(
        @RequestBody homeworkRequestDto: HomeworkRequestDto
    ): HomeWork {
        val (title, description,tasks) = homeworkRequestDto
        println(tasks.first().correctAnswer::class)
        println(TaskAnswerType.TaskRightAnswer.TextAnswer::class)
        println(tasks.first().correctAnswer is TaskAnswerType.TaskRightAnswer.TextAnswer)
        return homeworkService.createHomework(
            HomeWork(
                title,
                description,
//                deadline,
                tasks
//            setOf(Task("a","",1,TaskAnswerType.TaskRightAnswer.TextAnswer(TaskType.TEXT_INPUT,"")))
            )
        )
    }

    @PostMapping(HomeworksRoute.HOMEWORK_ANSWERS)
    fun createAnswerOnHomework(
        @RequestBody homeworkAnswerRequestDTO: HomeworkAnswerRequestDTO
    ) {
        homeworkService.createAnswerOnHomework(
            HomeworkAnswer(
                homeworkAnswerRequestDTO.homeworkId,
                homeworkAnswerRequestDTO.answers
            )
        )
    }
}