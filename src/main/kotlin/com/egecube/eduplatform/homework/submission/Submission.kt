package com.egecube.eduplatform.homework.submission

import com.egecube.eduplatform.homework.attachment.Attachment
import com.egecube.eduplatform.homework.Homework
import com.egecube.eduplatform.homework.task.TaskAnswer
import com.egecube.eduplatform.homework.task.TaskType
import java.time.LocalDateTime

data class Submission(
    val submissionId: Long,
    val studentId: Long,
    val homework: Homework,
    val studentAnswers: List<TaskAnswer<TaskType>>,
    val attachments: List<Attachment>,
    val dateSubmitted: LocalDateTime
)

