package com.egecube.eduplatform.homeworksManagement.internal.domain

import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.time.ZonedDateTime

@Document(collection = "homeworks")
class HomeWork(
//    @Id
//    val id: String? = ,
    val title: String,
    val description: String,
//    val deadline: ZonedDateTime,
    val tasks: Set<Task>,

    @DBRef
    val answers: List<HomeworkAnswer>? = listOf()
)