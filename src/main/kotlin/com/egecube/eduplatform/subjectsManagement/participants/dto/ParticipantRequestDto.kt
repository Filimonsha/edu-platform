package com.egecube.eduplatform.subjectsManagement.participants.dto

import com.egecube.eduplatform.subjectsManagement.courses.Course
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

/**
 * A DTO for the [Course] entity
 */

data class ParticipantRequestDto(
    @JsonProperty("name")
    val name: String,
    val secondName: String,
    val email: String,
    val participantRole: String
) : Serializable