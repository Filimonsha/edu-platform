package com.egecube.eduplatform.homeworksManagement.tasks.internal.domain

import jakarta.persistence.Id
import net.minidev.json.annotate.JsonIgnore
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "tasks")
open class Task(

    @Id
    @JsonIgnore
    var _id: String? = ObjectId().toString(),
    val homeworkId: String,
    val title: String,
    val description: String,
    val priority: Long,
    val correctAnswer: TaskAnswerType.TaskRightAnswer,
//    val attachments: List<Attachment>,
)