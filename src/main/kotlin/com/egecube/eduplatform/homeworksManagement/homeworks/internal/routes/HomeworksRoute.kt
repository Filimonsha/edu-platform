package com.egecube.eduplatform.homeworksManagement.homeworks.internal.routes

object HomeworksRoute {
    private const val BASE_ROUTE = "/api/homewroks-management"
    const val HOMEWORKS = "$BASE_ROUTE/homeworks"
    const val HOMEWORK = "$BASE_ROUTE/homeworks/{homeworkId}"

    const val HOMEWORK_TASKS = "$HOMEWORK/tasks"
    const val HOMEWORK_ANSWERS = "$HOMEWORK/answers"
}