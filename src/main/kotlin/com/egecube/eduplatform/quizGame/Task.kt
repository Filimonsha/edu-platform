package com.egecube.eduplatform.quizGame

data class Task(
    val id: Long = 13,
    val desc: String = "Simple question",
    val answers: ArrayList<String> = arrayListOf("Right", "Wrong"),
    val right: Int = 0
)