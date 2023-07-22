package com.egecube.eduplatform.tasksManagement.dto

import com.egecube.eduplatform.tasksManagement.domain.SimpleTask

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
