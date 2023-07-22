package com.egecube.eduplatform.tasksManagement.tasks

data class SimpleTaskDto(
    val id: String,
    val desc: String,
    val answers: ArrayList<String>
) {
    constructor(obj: SimpleTask) : this(
        id = obj.id.toHexString(),
        desc = obj.desc,
        answers = obj.answers
    )
}
