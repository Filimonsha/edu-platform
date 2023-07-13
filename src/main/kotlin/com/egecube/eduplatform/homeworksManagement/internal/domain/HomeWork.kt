package com.egecube.eduplatform.homeworksManagement.internal.domain

import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.sql.Timestamp
import java.time.ZonedDateTime
import java.util.Date

@Document(collection = "homeworks")
class HomeWork(
//    @Id
//    val id: String? = ,
    val title: String,
    val description: String,
    val deadline: ZonedDateTime,
//    val deadline: Timestamp,
    val tasks: Set<Task>,

    @DBRef
    val answers: List<HomeworkAnswer>? = listOf()
)