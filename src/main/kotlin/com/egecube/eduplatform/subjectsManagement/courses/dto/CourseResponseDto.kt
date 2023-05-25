package com.egecube.eduplatform.subjectsManagement.courses.dto

import java.io.Serializable
import com.egecube.eduplatform.subjectsManagement.courses.Course

/**
 * A DTO for the  entity [Course]
 */

data class CourseResponseDto(val id: Long?, val name: String, val subject: Long?, val participants: Set<Long?>) : Serializable