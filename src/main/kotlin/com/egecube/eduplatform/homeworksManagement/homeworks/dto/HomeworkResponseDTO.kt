package com.egecube.eduplatform.homeworksManagement.homeworks.dto

import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.Homework
import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.Task
import java.io.Serializable
import java.time.ZonedDateTime

/**
 * A DTO for the [Homework] entity
 */

data class HomeworkResponseDTO(
    val _id: String,
    val title: String,
    val subjectId: Long,
    val creatorId: String,
    val description: String,
    val deadline: ZonedDateTime,
    val tasks: MutableSet<Task>,
    val solvers: MutableList<Long> = mutableListOf(),
    val answers: MutableSet<String>?
) : Serializable