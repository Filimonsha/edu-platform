package com.egecube.eduplatform.homeworksManagement.tasks.dto

import com.egecube.eduplatform.homeworksManagement.tasks.domain.Task
import com.egecube.eduplatform.homeworksManagement.tasks.domain.TaskAnswerType
import java.io.Serializable

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