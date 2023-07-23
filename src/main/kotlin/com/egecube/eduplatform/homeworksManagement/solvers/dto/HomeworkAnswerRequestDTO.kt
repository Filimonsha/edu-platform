package com.egecube.eduplatform.homeworksManagement.solvers.dto

import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.HomeworkAnswer
import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.TaskAnswer

/**
 * A DTO for the [HomeworkAnswer] entity
 */
data class HomeworkAnswerRequestDTO(
    val homeworkId: String,
    val tasksAnswers: List<TaskAnswer>
)