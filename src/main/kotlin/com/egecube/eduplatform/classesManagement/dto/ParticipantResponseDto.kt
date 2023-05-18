package com.egecube.eduplatform.classesManagement.dto

import java.io.Serializable

/**
 * A DTO for the {@link com.egecube.eduplatform.classesManagement.domain.Participant} entity
 */
data class ParticipantResponseDto(
    var id: Long? = null,
    var name: String? = null,
    var secondName: String? = null,
    var email: String? = null,
    var participantRole: String?,
    var relatedSubjects: Set<ParticipantSubjectDto?> = setOf(),
    var relatedFlows: Set<ParticipantFlowDto?> = setOf()
) : Serializable {
    /**
     * A DTO for the {@link com.egecube.eduplatform.classesManagement.domain.Subject} entity
     */
    data class ParticipantSubjectDto(var id: Long? = null) : Serializable

    /**
     * A DTO for the {@link com.egecube.eduplatform.classesManagement.domain.Flow} entity
     */
    data class ParticipantFlowDto(var id: Long? = null) : Serializable
}