package com.egecube.eduplatform.homeworksManagement.task

import com.egecube.eduplatform.homeworksManagement.internal.domain.TaskAnswerType

//TODO: generic T might a java type like string or long. We need to match it with TaskAnswerType somewhere
data class TaskAnswer<T>(val answer: T, val answerType: TaskAnswerType) {
}