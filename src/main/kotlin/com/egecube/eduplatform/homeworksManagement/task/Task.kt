package com.egecube.eduplatform.homeworksManagement.task

import com.egecube.eduplatform.homeworksManagement.attachment.Attachment
import com.egecube.eduplatform.homeworksManagement.internal.domain.TaskType

data class Task(
    val taskId: Long,
    val title: String,
    val description: String,
    val taskType: TaskType,
    val correctAnswer: TaskAnswer<TaskType>,
    val attachments: List<Attachment>,
)
