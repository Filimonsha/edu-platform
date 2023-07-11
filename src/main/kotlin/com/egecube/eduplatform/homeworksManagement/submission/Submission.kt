package com.egecube.eduplatform.homeworksManagement.submission

import com.egecube.eduplatform.homeworksManagement.attachment.Attachment
import com.egecube.eduplatform.homeworksManagement.Homework
import com.egecube.eduplatform.homeworksManagement.task.TaskAnswer
import com.egecube.eduplatform.homeworksManagement.internal.domain.TaskType
import java.time.LocalDateTime

data class Submission(
    val submissionId: Long,
    val studentId: Long,
    val homework: Homework,
    val studentAnswers: List<TaskAnswer<TaskType>>,
    val attachments: List<Attachment>,
    val dateSubmitted: LocalDateTime
)

