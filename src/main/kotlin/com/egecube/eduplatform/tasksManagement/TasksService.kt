package com.egecube.eduplatform.tasksManagement

import com.egecube.eduplatform.tasksManagement.internal.TaskRepository
import org.springframework.stereotype.Service


@Service
class TasksService(
//    private val taskRepository: TaskRepository
) {
    fun getNumberOfSimpleTasks(count: Int): List<SimpleTask> {
        return listOf(SimpleTask(1), SimpleTask(2), SimpleTask(3), SimpleTask(4), SimpleTask(5),
            SimpleTask(6), SimpleTask(7), SimpleTask(8), SimpleTask(9), SimpleTask(10))
    }

    fun getTaskById(): SimpleTask {
        return SimpleTask()
    }
}