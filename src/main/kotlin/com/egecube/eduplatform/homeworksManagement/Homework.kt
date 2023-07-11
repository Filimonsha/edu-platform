package com.egecube.eduplatform.homeworksManagement

import com.egecube.eduplatform.homeworksManagement.evaluation.Criteria
import com.egecube.eduplatform.homeworksManagement.task.Task
import java.time.LocalDateTime

data class Homework(
    val homeworkId: Long,
    val title: String,
    val description: String,
    val tasks: List<Task>,
    val deadline: LocalDateTime,
    val criteria: Criteria
)

