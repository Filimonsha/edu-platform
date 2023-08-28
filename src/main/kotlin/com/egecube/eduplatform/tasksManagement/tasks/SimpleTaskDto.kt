package com.egecube.eduplatform.tasksManagement.tasks

data class SimpleTaskDto(
    val id: String,
    val subjectId: Int,
    val taskNum: Int,
    val desc: String,
    val answers: List<String>,
    val rightAnswer: String
) {
    constructor(obj: SimpleTask) : this(
        id = obj._id.toHexString(),
        subjectId = obj.subjectId,
        taskNum = obj.taskNum,
        desc = obj.desc,
        answers = obj.answers,
        rightAnswer = obj.rightAnswer
    )
}
