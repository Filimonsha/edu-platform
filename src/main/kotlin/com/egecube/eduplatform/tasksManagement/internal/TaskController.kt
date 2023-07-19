package com.egecube.eduplatform.tasksManagement.internal

import com.egecube.eduplatform.tasksManagement.domain.SimpleTask
import com.egecube.eduplatform.tasksManagement.TaskService
import com.egecube.eduplatform.tasksManagement.consts.TaskRoutes
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TaskController(
    private val taskService: TaskService
) {

    @GetMapping(TaskRoutes.TASK_ROUTE)
    fun getTaskById(
        @PathVariable taskId: String
    ): SimpleTask {
        return taskService.getTaskById(taskId)
    }

    @GetMapping(TaskRoutes.TASKS_ROUTE)
    fun getNumberOfTasks(
        @RequestParam c: Int = 10
    ): List<SimpleTask> {
        return taskService.getNumberOfSimpleTasks(c)
    }
}