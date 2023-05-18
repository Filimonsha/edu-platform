package com.egecube.eduplatform.homework.task


sealed class TaskAnswerType {
    object Text : TaskAnswerType()
    object ChosenNumber : TaskAnswerType()
    object MultipleChosenNumbers : TaskAnswerType()
}