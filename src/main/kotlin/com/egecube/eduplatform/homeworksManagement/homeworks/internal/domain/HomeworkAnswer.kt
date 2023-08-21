package com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain

import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.TaskAnswer
import jakarta.persistence.Id
import net.minidev.json.annotate.JsonIgnore
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.ZonedDateTime

@Document(collection = "homeworksAnswers")
data class HomeworkAnswer(
    @Id
    @JsonIgnore
    var _id: String? = ObjectId().toString(),
    val homeworkId: String,
    val solverId: Long,
    var answers: List<TaskAnswer>,
    var evaluate: Evaluate? = null
)

data class Evaluate(
    val grade: Double,
    val feedback: String,
    val dateValidated: ZonedDateTime? = null
)