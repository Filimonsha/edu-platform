package com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain

import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.TaskAnswer
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "homeworksAnswers")
open class HomeworkAnswer(

    val homeworkId: String,
    val answers: List<TaskAnswer>
)