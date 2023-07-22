package com.egecube.eduplatform.homeworksManagement.homeworks.dto

import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.Homework
import java.io.Serializable
import java.time.ZonedDateTime

/**
 * A DTO for the [Homework] entity
 */

data class HomeworkRequestDto(
    val title: String,
    val description: String,
    val deadline: ZonedDateTime,
) : Serializable