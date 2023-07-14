package com.egecube.eduplatform.homeworksManagement.utils.evaluation

import java.time.LocalDateTime

data class Validation(
    val validationId: Long,
    val grade: Double,
    val feedback: String,
    val dateValidated: LocalDateTime
)
