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
    fun getNumberOfSimpleTasks(count: Int): List<Long> {
        return listOf(1, 2, 3)
    }

    fun getTaskById(): Task {
        return Task()
    }
}