package com.egecube.eduplatform.flowsManagement.dto

import java.io.Serializable

/**
 * A DTO for the {@link com.egecube.eduplatform.flowsManagement.domain.FlowGroup} entity
 */
data class FlowGroupdDto(var id: Long? = null, var name: String? = null, var flowId: Long? = null) : Serializable