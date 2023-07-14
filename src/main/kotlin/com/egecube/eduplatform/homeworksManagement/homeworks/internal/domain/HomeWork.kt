package com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain

import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.Task
import jakarta.persistence.Id
import net.minidev.json.annotate.JsonIgnore
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.ZonedDateTime

@Document(collection = "homeworks")
class HomeWork(
    @Id
    @JsonIgnore
    var _id: String? = ObjectId().toString(),
    val title: String,
    val description: String,
    val deadline: ZonedDateTime,

//    @DBRef
    val tasks: MutableSet<Task> = mutableSetOf(),

    val answers: MutableSet<HomeworkAnswer> = mutableSetOf()
)