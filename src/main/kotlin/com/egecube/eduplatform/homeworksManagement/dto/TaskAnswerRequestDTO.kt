package com.egecube.eduplatform.homeworksManagement.dto

import com.egecube.eduplatform.homeworksManagement.internal.domain.TaskAnswer
import com.egecube.eduplatform.homeworksManagement.internal.domain.TaskAnswerType

/**
 * A DTO for the [TaskAnswer] entity
 */

data class TaskAnswerRequestDTO (
    val taskType: TaskAnswerType,
    val data: TaskAnswerType,
)


//DTO<T>
//        1DTO<T>
//{
//    data:T
//}