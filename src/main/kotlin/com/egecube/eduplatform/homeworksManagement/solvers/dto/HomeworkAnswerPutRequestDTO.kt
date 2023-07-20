package com.egecube.eduplatform.homeworksManagement.solvers.dto

import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.Evaluate
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.HomeworkAnswer
import com.egecube.eduplatform.homeworksManagement.tasks.dto.TaskAnswerPutRequestDTO

/**
 * A DTO for the [HomeworkAnswer] entity
 */
data class HomeworkAnswerPutRequestDTO(
    var evaluate: Evaluate,
    val tasksAnswer: List<TaskAnswerPutRequestDTO>
)