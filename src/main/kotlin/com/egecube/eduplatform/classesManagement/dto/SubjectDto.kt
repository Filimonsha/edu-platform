package com.egecube.eduplatform.classesManagement.dto

import java.io.Serializable

/**
 * A DTO for the {@link com.egecube.eduplatform.classesManagement.domain.Subject} entity
 */
data class SubjectDto(
    var id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    var flows: MutableSet<SubjectFlowDto?>? = null
) : Serializable