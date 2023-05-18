package com.egecube.eduplatform.homework.task

import com.egecube.eduplatform.homework.attachment.Attachment

data class Task(
    val taskId: Long,
    val title: String,
    val description: String,
    val taskType: TaskType,
    val correctAnswer: TaskAnswer<TaskType>,
    val attachments: List<Attachment>,
)
