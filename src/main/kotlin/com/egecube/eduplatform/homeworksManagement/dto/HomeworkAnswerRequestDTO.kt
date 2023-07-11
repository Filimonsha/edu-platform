package com.egecube.eduplatform.homeworksManagement.dto

import com.egecube.eduplatform.homeworksManagement.internal.domain.HomeworkAnswer
import com.egecube.eduplatform.homeworksManagement.internal.domain.TaskAnswerType

/**
 * A DTO for the [HomeworkAnswer] entity
 */

data class HomeworkAnswerRequestDTO(
    val homeworkId: String,
    val answers: List<TaskAnswerType>
)