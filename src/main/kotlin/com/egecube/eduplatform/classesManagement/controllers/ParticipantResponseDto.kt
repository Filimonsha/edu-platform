package com.egecube.eduplatform.classesManagement.controllers

import com.egecube.eduplatform.classesManagement.domain.ParticipantRole
import java.io.Serializable

/**
 * A DTO for the {@link com.egecube.eduplatform.classesManagement.domain.Participant} entity
 */
data class ParticipantResponseDto(
    var id: Long? = null,
    var name: String? = null,
    var secondName: String? = null,
    var email: String? = null,
    var participantRole: String? = null,
    var relatedSubjects: MutableSet<ParticipantSubjectDto?>? = null,
    var relatedFlows: MutableSet<ParticipantFlowDto?>? = null
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