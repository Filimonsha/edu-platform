package com.egecube.eduplatform.homeworksManagement.tasks.internal.domain

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo


sealed class TaskAnswerType {
    class TaskUserAnswer(
        val answerIsCorrect: Boolean,
        val answer: TaskRightAnswer
    ) : TaskAnswerType()

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
    @JsonSubTypes(
        JsonSubTypes.Type(TaskRightAnswer.TextAnswer::class, name = TaskType.TEXT_INPUT),
        JsonSubTypes.Type(TaskRightAnswer.SingleAnswer::class, name = TaskType.SINGLE_CHOICE),
        JsonSubTypes.Type(TaskRightAnswer.MultipleAnswer::class, name = TaskType.MULTIPLE_ANSWER),
        JsonSubTypes.Type(
            TaskRightAnswer.AnswerWithAttachment::class,
            name = TaskType.ANSWER_WITH_ATTACHMENT
        ),
    )
    open class TaskRightAnswer(
    ) : TaskAnswerType() {
        data class TextAnswer(
            val answer: String = ""
        ) : TaskRightAnswer()

        class SingleAnswer(
            val answer: Number = 0,
        ) : TaskRightAnswer()

        class MultipleAnswer(
            val answer: List<Number> = listOf()
        ) : TaskRightAnswer()

        class AnswerWithAttachment(
            val answer: String? = null
        ) : TaskRightAnswer()
    }
}