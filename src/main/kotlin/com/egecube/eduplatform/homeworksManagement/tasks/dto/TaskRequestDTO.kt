package com.egecube.eduplatform.homeworksManagement.tasks.dto

import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.Task
import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.TaskAnswerType
import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable

/**
 * A DTO for the [Task] entity
 */
@Schema(name = "Прикрепление задачи к ДЗ")
data class TaskRequestDTO(
    @Schema(description = "По умолчанию необходимо брать из индекса задания")
    val title: String,
    val description: String,
    @Schema(
        name = "Номер очередности задания",
        description = "По умолчанию необходимо брать из индекса задания"
    )
    val priority: Long,

    @Schema(
        name = "Правильный овет на задание",
        description = "Тип задания указывается одной из следующих констант:TEXT_INPUT,SINGLE_CHOICE,MULTIPLE_ANSWER, ANSWER_WITH_ATTACHMENT"
    )
    val correctAnswer: TaskAnswerType.TaskRightAnswer
) : Serializable