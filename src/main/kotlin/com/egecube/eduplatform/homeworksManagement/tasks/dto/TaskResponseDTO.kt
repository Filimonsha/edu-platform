package com.egecube.eduplatform.homeworksManagement.tasks.dto

import java.io.Serializable
import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.Task
import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.TaskAnswerType

/**
 * A DTO for the [Task] entity
 */

data class TaskResponseDTO(
    val homeworkId: String,
    val title: String,
    val description: String,
    val priority: Long,
    val correctAnswer: TaskAnswerType.TaskRightAnswer
) : Serializable