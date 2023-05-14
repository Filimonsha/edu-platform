package com.egecube.eduplatform.classesManagement.dto

import java.io.Serializable

/**
 * A DTO for the {@link com.egecube.eduplatform.classesManagement.domain.Flow} entity
 */
data class FlowDto(var id: Long? = null, var name: String? = null, var subjectId: Long? = null) : Serializable