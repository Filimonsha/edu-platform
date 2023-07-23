package com.egecube.eduplatform.tasksManagement.tasks.internal

import com.egecube.eduplatform.tasksManagement.tasks.SimpleTaskDto
import com.egecube.eduplatform.tasksManagement.tasks.SimpleTaskService
import com.egecube.eduplatform.tasksManagement.tasks.consts.TaskRoutes
import com.egecube.eduplatform.tasksManagement.tasks.dto.NewTaskDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TaskController(
    private val taskService: SimpleTaskService
) {

    @GetMapping(TaskRoutes.TASK_ROUTE)
    fun getTaskById(
        @PathVariable taskId: String
    ): SimpleTaskDto {
        return taskService.getTaskById(taskId)
    }

    @GetMapping(TaskRoutes.TASKS_ROUTE)
    fun getNumberOfTasks(
        @RequestParam count: Int = 10
    ): List<SimpleTaskDto> {
        return taskService.getNumberOfSimpleTasks(count)
    }

    @GetMapping(TaskRoutes.SUBJECT_TASKS)
    fun getNumberOfSubjectTasks(
        @PathVariable subjectId: String,
        @RequestParam count: Int = 10
    ): List<SimpleTaskDto> {
        return taskService.getNumberOfSubjectTasks(count, subjectId.toInt())
    }

    @PostMapping(TaskRoutes.TASKS_ROUTE)
    fun addSimpleTaskForSubject(
        @RequestParam newTask: NewTaskDto
    ): SimpleTaskDto {
        return taskService.addTaskBySubject(newTask)
    }
}