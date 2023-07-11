package com.egecube.eduplatform.homeworksManagement.internal.domain

import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document
class TaskAnswer<T>(
    @DBRef(lazy = true)
    val taskId: String,
    val answer: T
)