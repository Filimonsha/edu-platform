package com.egecube.eduplatform.subjectsManagement.courses.dto

import com.egecube.eduplatform.subjectsManagement.Course
import java.io.Serializable

/**
 * A DTO for the  entity [Course]
 */

data class CourseResponseDto(val id: Long?, val name: String, val subject: Long?, val participants: Set<Long?>) :
    Serializable