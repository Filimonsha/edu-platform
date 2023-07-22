package com.egecube.eduplatform.tasksManagement.tasks

import org.bson.types.ObjectId
import org.springframework.stereotype.Service


@Service
class TaskService(
//    private val taskRepository: TaskRepository
) {
    fun getNumberOfSimpleTasks(count: Int): List<SimpleTask> {
        return listOf(
            SimpleTask(ObjectId("64b651db7cdf8b3f488ab9c7")), SimpleTask(ObjectId("64b652207cdf8b3f488ab9c8")),
            SimpleTask(ObjectId("64b652687cdf8b3f488ab9c9")), SimpleTask(ObjectId("64b652c97cdf8b3f488ab9ca")),
            SimpleTask(ObjectId("64b653017cdf8b3f488ab9cb")), SimpleTask(ObjectId("64b6534f7cdf8b3f488ab9cc")),
            SimpleTask(ObjectId("64b659c9a633500bd7187dce")), SimpleTask(ObjectId("64b65a4e67dfe7219542c9bf")),
            SimpleTask(ObjectId("64b65cd767dfe7219542c9c0")), SimpleTask(ObjectId("64b65d4267dfe7219542c9c1"))
        )
    }

    fun getTaskById(taskId: String): SimpleTask {
        return SimpleTask(ObjectId(taskId))
    }
}