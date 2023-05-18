package com.egecube.eduplatform.classesManagement.dto

import com.egecube.eduplatform.classesManagement.domain.ParticipantRole
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

/**
 * A DTO for the {@link com.egecube.eduplatform.classesManagement.domain.Participant} entity
 */
data class ParticipantRequestDto(
    @JsonProperty("name")
    var name: String?,
    var secondName: String?,
    var email: String?,
    var participantRole: String?
) : Serializable