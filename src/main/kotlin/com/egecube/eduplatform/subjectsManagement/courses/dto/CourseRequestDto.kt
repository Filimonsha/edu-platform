package com.egecube.eduplatform.subjectsManagement.courses.dto

import com.egecube.eduplatform.subjectsManagement.courses.Course
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

/**
 * A DTO for the [Course] entity
 */
data class CourseRequestDto(
    @JsonProperty("name")
    val name: String
) : Serializable