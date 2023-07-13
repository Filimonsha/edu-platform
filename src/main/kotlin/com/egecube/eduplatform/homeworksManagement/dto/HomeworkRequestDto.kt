package com.egecube.eduplatform.homeworksManagement.dto

import com.egecube.eduplatform.homeworksManagement.Homework
import com.egecube.eduplatform.homeworksManagement.internal.domain.Task
import java.io.Serializable
import java.sql.Timestamp
import java.time.ZonedDateTime
import java.util.Date

/**
 * A DTO for the [Homework] entity
 */

data class HomeworkRequestDto(
    val title: String,
    val description: String,
    val deadline: ZonedDateTime,
//    val deadline: Timestamp,
    val tasks: Set<Task>
) : Serializable