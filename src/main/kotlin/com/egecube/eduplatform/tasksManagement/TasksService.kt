package com.egecube.eduplatform.tasksManagement

import com.egecube.eduplatform.tasksManagement.internal.TaskRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.AggregationResults
import org.springframework.data.mongodb.core.aggregation.SampleOperation
import org.springframework.stereotype.Service


@Service
class TasksService(
    private val taskRepository: TaskRepository
) {
    fun getNumberOfSimpleTasks(count: Int): List<Task> {
        return listOf(Task(1), Task(2), Task(3), Task(4), Task(5),
            Task(6), Task(7), Task(8), Task(9), Task(10))
    }

    fun getTaskById(): Task {
        return Task()
    }
}