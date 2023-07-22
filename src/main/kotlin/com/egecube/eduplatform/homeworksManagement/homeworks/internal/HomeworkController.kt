package com.egecube.eduplatform.homeworksManagement.homeworks.internal

import com.egecube.eduplatform.homeworksManagement.attachments.AttachmentService
import com.egecube.eduplatform.homeworksManagement.homeworks.HomeworkService
import com.egecube.eduplatform.homeworksManagement.homeworks.dto.HomeworkRequestDto
import com.egecube.eduplatform.homeworksManagement.homeworks.dto.HomeworkResponseDTO
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.Homework
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.HomeworkAnswer
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.routes.HomeworksRoute
import com.egecube.eduplatform.homeworksManagement.tasks.dto.TaskRequestDTO
import com.egecube.eduplatform.homeworksManagement.tasks.dto.TaskResponseDTO
import com.egecube.eduplatform.homeworksManagement.tasks.domain.TaskAnswer
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.bson.types.Binary
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@Tag(
    name = "Homeworks-management. HomeworkController",
    description = "Позволяет создавать домашние задания и их задания, а также создавать ответы"
)
class HomeworkController(
    private val homeworkService: HomeworkService,
    private val attachmentService: AttachmentService,
) {

    @GetMapping(HomeworksRoute.HOMEWORKS)
    @Operation(summary = "Получение всех ДЗ")
    fun getAllHomeworks(): List<Homework> {
        return homeworkService.getAllHomeworks()
    }

    @PostMapping(HomeworksRoute.HOMEWORKS)
    @Operation(summary = "Создание ДЗ")
    fun createHomework(
        @RequestBody homeworkRequestDto: HomeworkRequestDto
    ): HomeworkResponseDTO {
        val (title, description, deadline) = homeworkRequestDto

        val createdHomework = homeworkService.createHomework(
            Homework(
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
    @Operation(summary = "Получение ДЗ")
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

    @GetMapping(HomeworksRoute.HOMEWORK_TASKS)
    @Operation(summary = "Получение заданий ДЗ")
    fun getHomeworkTasks(
        @PathVariable homeworkId: String
    ): List<TaskResponseDTO> {
        return homeworkService.getHomeworkTasks(homeworkId)
            .map { TaskResponseDTO(it.homeworkId, it.title, it.description, it.priority, it.correctAnswer) }
    }

    @PostMapping(HomeworksRoute.HOMEWORK_TASKS)
    @Operation(summary = "Прикрепление заданий к ДЗ")
    fun createHomeworkTasks(
        @PathVariable homeworkId: String,
        @RequestBody tasks: List<TaskRequestDTO>
    ): Homework {
        return homeworkService.addTasksToHomework(homeworkId, tasks)
    }

    @PostMapping(HomeworksRoute.HOMEWORK_ANSWERS)
    @Operation(summary = "Создание ответов к заданиям ДЗ")
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
    @Operation(summary = "Тестовое сохранение файла через хранение в BLOB")
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


}