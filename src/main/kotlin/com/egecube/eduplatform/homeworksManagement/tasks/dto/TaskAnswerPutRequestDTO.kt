package com.egecube.eduplatform.homeworksManagement.tasks.dto

import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.Evaluate
import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.TaskAnswer

/**
 * A DTO for the [TaskAnswer] entity
 */
data class TaskAnswerPutRequestDTO(
    val taskId: String,
    val evaluate: Evaluate? = null
)