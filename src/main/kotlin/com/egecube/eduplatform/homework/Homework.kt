package com.egecube.eduplatform.homework

import com.egecube.eduplatform.homework.evaluation.Criteria
import com.egecube.eduplatform.homework.task.Task
import java.time.LocalDateTime

data class Homework(
    val homeworkId: Long,
    val title: String,
    val description: String,
    val tasks: List<Task>,
    val deadline: LocalDateTime,
    val criteria: Criteria
)

