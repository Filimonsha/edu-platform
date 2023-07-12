package com.egecube.eduplatform.homeworksManagement.internal

import com.egecube.eduplatform.homeworksManagement.AttachmentService
import com.egecube.eduplatform.homeworksManagement.HomeworkService
import com.egecube.eduplatform.homeworksManagement.dto.HomeworkAnswerRequestDTO
import com.egecube.eduplatform.homeworksManagement.dto.HomeworkRequestDto
import com.egecube.eduplatform.homeworksManagement.internal.domain.*
import com.egecube.eduplatform.homeworksManagement.internal.routes.HomeworksRoute
import org.bson.types.Binary
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
class HomeworkController(
    private val homeworkService: HomeworkService,
    private val attachmentService: AttachmentService
) {
    @GetMapping(HomeworksRoute.HOMEWORKS)
    fun getAllHomeworks(): List<HomeWork> {
        return listOf(
            HomeWork(
                " title",
                "description",
//                deadline,
//                setOf()
                setOf(Task("a", "", 1, TaskAnswerType.TaskRightAnswer.TextAnswer("dfddd")))
            )
        )
//        return homeworkService.getAllHomeworks()
    }

    @PostMapping(HomeworksRoute.HOMEWORKS)
    fun createHomework(
        @RequestBody homeworkRequestDto: HomeworkRequestDto
    ): HomeWork {
        val (title, description, tasks) = homeworkRequestDto
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

    @PostMapping("/api/test")
    fun test(
//        @RequestBody attachmentRequstDto: AttachmentRequstDto
        @RequestParam("title") title: String,
        @RequestParam("file") file: MultipartFile
    ): Binary {

        return attachmentService.loadAttachment(title,file).data
    }

    @GetMapping("/api/test1")
    fun testGet(
//        @RequestBody attachmentRequstDto: AttachmentRequstDto
//        @RequestParam("title") title: String,
//        @RequestParam("file") file: MultipartFile
    ): String {


        return "data:image/png;base64, " +  Base64.getEncoder().encodeToString(attachmentService.getAttachemnt().data.data)
    }
}