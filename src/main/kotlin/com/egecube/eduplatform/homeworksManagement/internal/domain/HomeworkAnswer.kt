package com.egecube.eduplatform.homeworksManagement.internal.domain

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "homeworksAnswers")
open class HomeworkAnswer(

    val homeworkId: String,
    val answers: List<TaskAnswerType>
)