package com.egecube.eduplatform.homeworksManagement.internal.domain

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
sealed class TaskAnswerType {
//    abstract val taskType: TaskType

    //    class TaskUserAnswer(
//        val taskId: String
//    ) : TaskRightAnswer() {
//
//    }
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
    @JsonSubTypes(
        JsonSubTypes.Type(TaskRightAnswer.TextAnswer::class, name = TaskType.TEXT_INPUT),
        JsonSubTypes.Type(TaskRightAnswer.SelectAnswer::class, name = TaskType.SINGLE_CHOICE),
        )
    open class TaskRightAnswer(
        // override val taskType: TaskType = TaskType.TEXT_INPUT,
        //open val answer: Any? = null
    ) : TaskAnswerType() {
        data class TextAnswer(
//            override val taskId: String,
//            @Field
//            override val taskType: TaskType = TaskType.TEXT_INPUT,
            val answer: String = ""
            ) : TaskRightAnswer()

        class SelectAnswer(
//            override val taskId: String,
//            override val taskType: TaskType = TaskType.SINGLE_CHOICE,

            val answer: Number,
        ) : TaskRightAnswer()
    }

//    class TextAnswer(
//        override val taskId: String,
//        override val taskType: TaskType = TaskType.TEXT_INPUT,
//        val answer: String,
//    ) : TaskAnswerType()
//
//    class SelectAnswer(
//        override val taskId: String,
//        override val taskType: TaskType = TaskType.SINGLE_CHOICE,
//
//        val answer: Number,
//    ) : TaskAnswerType()
    //    object Text : TaskAnswerType()
//    object ChosenNumber : TaskAnswerType()
//    object MultipleChosenNumbers : TaskAnswerType()
}

class Test(
    answer: String
)