package com.egecube.eduplatform.tasksManagement

data class SimpleTask(
    val id: Long = 13,
    val desc: String = "Simple question",
    val answers: ArrayList<String> = arrayListOf("Right", "Wrong"),
    val rightAnswer: String = "0"
)