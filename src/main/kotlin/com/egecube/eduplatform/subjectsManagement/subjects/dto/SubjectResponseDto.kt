package com.egecube.eduplatform.subjectsManagement.subjects.dto

import com.egecube.eduplatform.subjectsManagement.subjects.Subject
import java.io.Serializable

/**
 * A DTO for the [Subject] entity
 */

data class SubjectResponseDto(
    val id: Long?,
    val name: String,
    val description: String,
    val courses: Set<Long?> = setOf(),
    val participants: Set<Long?> = setOf()
) : Serializable
