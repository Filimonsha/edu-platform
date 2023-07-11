package com.egecube.eduplatform.homeworksManagement.evaluation

import com.egecube.eduplatform.homeworksManagement.submission.Submission
import java.time.LocalDateTime

data class Validation(
    val validationId: Long,
    val submission: Submission,
    val grade: Double,
    val feedback: String,
    val dateValidated: LocalDateTime
)
