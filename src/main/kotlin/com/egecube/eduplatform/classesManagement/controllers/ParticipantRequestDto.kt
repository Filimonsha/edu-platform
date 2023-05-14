package com.egecube.eduplatform.classesManagement.controllers

import com.egecube.eduplatform.classesManagement.domain.ParticipantRole
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

/**
 * A DTO for the {@link com.egecube.eduplatform.classesManagement.domain.Participant} entity
 */
data class ParticipantRequestDto(
    @JsonProperty("name")
    var name: String? = null,
    var secondName: String? = null,
    var email: String? = null,
    var participantRole: String? = null
) : Serializable