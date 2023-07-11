package com.egecube.eduplatform.homeworksManagement.internal.domain

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "tasks")
open class Task(
//    @Id
//    val id: String,
    val title: String,
    val description: String,
    val priority: Long,
    val correctAnswer: TaskAnswerType.TaskRightAnswer,
//    val attachments: List<Attachment>,
)