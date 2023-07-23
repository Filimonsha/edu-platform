package com.egecube.eduplatform.tasksManagement.tasks.dto

data class NewTaskDto (
    val subjectId: Int,
    val desc: String,
    val answers: List<String>
)