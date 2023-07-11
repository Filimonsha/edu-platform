package com.egecube.eduplatform.homeworksManagement.dto

import java.io.Serializable
import java.time.LocalDateTime
import com.egecube.eduplatform.homeworksManagement.Homework
import com.egecube.eduplatform.homeworksManagement.internal.domain.Task
import java.time.ZonedDateTime

/**
 * A DTO for the [Homework] entity
 */

data class HomeworkRequestDto(
    val title: String,
    val description: String,
//    val deadline: ZonedDateTime,
    val tasks: Set<Task>
) : Serializable