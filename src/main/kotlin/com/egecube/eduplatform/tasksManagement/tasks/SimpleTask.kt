package com.egecube.eduplatform.tasksManagement.tasks

import com.egecube.eduplatform.tasksManagement.tasks.consts.Tables
import jakarta.persistence.Id
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(Tables.TASK_TABLE)
data class SimpleTask(
    @Id
    val _id: ObjectId = ObjectId(),
    val subjectId: Int,
    val taskNum: Int,
    val desc: String,
    val answers: List<String> = emptyList(),
    val rightAnswer: String
)