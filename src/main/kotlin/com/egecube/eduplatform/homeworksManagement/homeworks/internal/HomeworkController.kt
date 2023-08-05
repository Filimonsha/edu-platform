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
    @Operation(summary = "Получение всех ДЗ по создателю")
    fun getAllHomeworks(@RequestParam creatorId: String): List<Homework> {
        return homeworkService.getAllHomeworksByCreatorId(creatorId)
    }

    @PostMapping(HomeworksRoute.HOMEWORKS)
    @Operation(summary = "Создание ДЗ для групп")
    fun createHomework(
        @RequestBody homeworkRequestDto: HomeworkRequestDto
    ): HomeworkResponseDTO {

        val createdHomework = homeworkService.createHomework(
            homeworkRequestDto,
        )
        return HomeworkResponseDTO(
            createdHomework._id.toString(),
            createdHomework.title,
            createdHomework.subjectId,
            createdHomework.creatorId,
            createdHomework.description,
            createdHomework.deadline,
            createdHomework.tasks,
            createdHomework.solvers,
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
            foundHomework.subjectId,
            foundHomework.creatorId,
            foundHomework.description,
            foundHomework.deadline,
            foundHomework.tasks,
            foundHomework.solvers,
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


    @GetMapping(HomeworksRoute.HOMEWORK_ANSWERS)
    @Operation(summary = "Получение всех ответов на ДЗ")
    fun readAllHomeworkAnswers(@PathVariable homeworkId: String): List<HomeworkAnswer> {
        return homeworkService.getAllHomeworkAnswers(homeworkId)
    }

    @PostMapping("/api/test")
    @Operation(summary = "Тестовое сохранение файла через хранение в BLOB")
    fun test(
//        @RequestBody attachmentRequstDto: AttachmentRequstDto
        @RequestParam("title") title: String,
        @RequestParam("file") file: MultipartFile
    ): Binary {

        return attachmentService.saveAttachment(title, file).data
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