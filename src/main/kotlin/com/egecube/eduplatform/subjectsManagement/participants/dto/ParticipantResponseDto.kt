package com.egecube.eduplatform.subjectsManagement.participants.dto

import com.egecube.eduplatform.subjectsManagement.participants.domain.Participant
import java.io.Serializable

/**
 * A DTO for the [Participant] entity
 */

data class ParticipantResponseDto(
    val id: Long?,
    val name: String,
    val secondName: String,
    val email: String,
    val participantRole: String,
    val relatedSubjects: Set<Long?>,
    val relatedCourses: Set<Long?>,
) : Serializable {

}
