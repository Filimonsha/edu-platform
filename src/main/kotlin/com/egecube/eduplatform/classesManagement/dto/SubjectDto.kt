package com.egecube.eduplatform.classesManagement.dto

import java.io.Serializable

/**
 * A DTO for the {@link com.egecube.eduplatform.classesManagement.domain.Subject} entity
 */
data class SubjectDto(
    var id: Long?,
    var name: String?,
    var description: String?,
    var flows: MutableSet<SubjectFlowDto?>?,
) : Serializable