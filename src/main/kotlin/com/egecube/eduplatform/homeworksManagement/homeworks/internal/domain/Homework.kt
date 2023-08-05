package com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain

import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.Task
import jakarta.persistence.Id
import net.minidev.json.annotate.JsonIgnore
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.ZonedDateTime

@Document(collection = "homeworks")
class Homework(
    @Id
    @JsonIgnore
    var _id: String? = ObjectId().toString(),
    val creatorId: String,
    val subjectId: Long,
    val title: String,
    val description: String,
    val deadline: ZonedDateTime,

    val tasks: MutableSet<Task> = mutableSetOf(),

    val solvers: MutableList<Long> = mutableListOf(),

    val answers: MutableSet<String> = mutableSetOf()
)