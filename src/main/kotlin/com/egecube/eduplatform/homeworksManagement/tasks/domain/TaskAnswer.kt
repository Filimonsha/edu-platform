package com.egecube.eduplatform.homeworksManagement.tasks.domain

import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.Evaluate

data class TaskAnswer(
    val taskId: String,
    val taskAnswer: TaskAnswerType.TaskUserAnswer,
    var evaluate: Evaluate? = null
)