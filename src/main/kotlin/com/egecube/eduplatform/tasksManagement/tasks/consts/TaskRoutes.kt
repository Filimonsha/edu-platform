package com.egecube.eduplatform.tasksManagement.tasks.consts

object TaskRoutes {
    const val TASKS_ROUTE = "/api/tasks"
    const val TASK_ROUTE = "$TASKS_ROUTE/{taskId}"
    const val SUBJECT_TASKS = "$TASKS_ROUTE/subject/{subjectId}"
}