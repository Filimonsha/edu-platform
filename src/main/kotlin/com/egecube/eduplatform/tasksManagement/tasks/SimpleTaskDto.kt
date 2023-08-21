package com.egecube.eduplatform.tasksManagement.tasks

data class SimpleTaskDto(
    val id: String,
    val subjectId: Int,
    val desc: String,
    val answers: List<String>,
) {
    constructor(obj: SimpleTask) : this(
        id = obj._id.toHexString(),
        subjectId = obj.subjectId,
        desc = obj.desc,
        answers = obj.answers
    )
}
