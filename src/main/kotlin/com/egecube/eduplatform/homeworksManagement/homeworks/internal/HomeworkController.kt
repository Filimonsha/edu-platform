package com.egecube.eduplatform.homeworksManagement.homeworks.internal

import com.egecube.eduplatform.homeworksManagement.attachments.AttachmentService
import com.egecube.eduplatform.homeworksManagement.homeworks.HomeworkService
import com.egecube.eduplatform.homeworksManagement.dto.*
import com.egecube.eduplatform.homeworksManagement.homeworks.dto.HomeworkRequestDto
import com.egecube.eduplatform.homeworksManagement.homeworks.dto.HomeworkResponseDTO
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.HomeWork
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.HomeworkAnswer
import com.egecube.eduplatform.homeworksManagement.internal.domain.*
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.routes.HomeworksRoute
import com.egecube.eduplatform.homeworksManagement.tasks.dto.TaskRequestDTO
import com.egecube.eduplatform.homeworksManagement.tasks.dto.TaskResponseDTO
import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.TaskAnswer
import org.bson.types.Binary
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
class HomeworkController(
    private val homeworkService: HomeworkService,
    private val attachmentService: AttachmentService,
) {
    @GetMapping(HomeworksRoute.HOMEWORKS)
    fun getAllHomeworks(): List<HomeWork> {
        return homeworkService.getAllHomeworks()
    }

    @PostMapping(HomeworksRoute.HOMEWORKS)
    fun createHomework(
        @RequestBody homeworkRequestDto: HomeworkRequestDto
    ): HomeworkResponseDTO {
        val (title, description, deadline) = homeworkRequestDto

        val createdHomework = homeworkService.createHomework(
            HomeWork(
                null,
                title,
                description,
                deadline
            )
        )
        return HomeworkResponseDTO(
            createdHomework._id.toString(),
            createdHomework.title,
            createdHomework.description,
            createdHomework.deadline,
            createdHomework.tasks,
            createdHomework.answers
        )
    }


    @GetMapping(HomeworksRoute.HOMEWORK)
    fun getHomework(@PathVariable homeworkId: String): HomeworkResponseDTO {
        val foundHomework = homeworkService.getHomework(homeworkId)
        return HomeworkResponseDTO(
            foundHomework._id.toString(),
            foundHomework.title,
            foundHomework.description,
            foundHomework.deadline,
            foundHomework.tasks,
            foundHomework.answers
        )
    }

    @PostMapping(HomeworksRoute.HOMEWORK_ANSWERS)
    fun createAnswerOnHomework(
        @PathVariable homeworkId: String,
        @RequestBody tasksAnswer: List<TaskAnswer>
    ) {
        homeworkService.createAnswerOnHomework(
            HomeworkAnswer(
                homeworkId,
                tasksAnswer
            )
        )
    }

    @PostMapping("/api/test")
    fun test(
//        @RequestBody attachmentRequstDto: AttachmentRequstDto
        @RequestParam("title") title: String,
        @RequestParam("file") file: MultipartFile
    ): Binary {

        return attachmentService.loadAttachment(title, file).data
    }

    @GetMapping("/api/test1")
    fun testGet(
//        @RequestBody attachmentRequstDto: AttachmentRequstDto
//        @RequestParam("title") title: String,
//        @RequestParam("file") file: MultipartFile
    ): String {


        return "data:image/png;base64, " + Base64.getEncoder()
            .encodeToString(attachmentService.getAttachemnt().data.data)
    }

    @GetMapping(HomeworksRoute.HOMEWORK_TASKS)
    fun getHomeworkTasks(
        @PathVariable homeworkId: String
    ): List<TaskResponseDTO> {
        return homeworkService.getHomeworkTasks(homeworkId)
            .map { TaskResponseDTO(it.homeworkId, it.title, it.description, it.priority, it.correctAnswer) }
    }

    @PostMapping(HomeworksRoute.HOMEWORK_TASKS)
    fun createHomeworkTasks(
        @PathVariable homeworkId: String,
        @RequestBody tasks: List<TaskRequestDTO>
    ): Unit {
        homeworkService.addTasksToHomework(homeworkId, tasks)
    }
}