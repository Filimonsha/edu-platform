package com.egecube.eduplatform.tasksManagement.tasks

import org.bson.types.ObjectId

data class SimpleTask(
    val id: ObjectId = ObjectId(),
    val desc: String = "Simple question",
    val answers: ArrayList<String> = arrayListOf("Right", "Wrong"),
    val rightAnswer: String = "Right"
)