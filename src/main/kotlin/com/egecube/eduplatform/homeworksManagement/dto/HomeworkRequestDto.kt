package com.egecube.eduplatform.homeworksManagement.dto

import com.egecube.eduplatform.homeworksManagement.Homework
import com.egecube.eduplatform.homeworksManagement.internal.domain.Task
import java.io.Serializable

/**
 * A DTO for the [Homework] entity
 */

data class HomeworkRequestDto(
    val title: String,
    val description: String,
//    val deadline: ZonedDateTime,
    val tasks: Set<Task>
) : Serializable