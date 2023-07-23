package com.egecube.eduplatform.tasksManagement.tasks

import com.egecube.eduplatform.tasksManagement.tasks.consts.Tables
import com.egecube.eduplatform.tasksManagement.tasks.dto.NewTaskDto
import com.egecube.eduplatform.tasksManagement.tasks.internal.SimpleTaskRepository
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.MatchOperation
import org.springframework.data.mongodb.core.aggregation.SampleOperation
import org.springframework.stereotype.Service


@Service
class SimpleTaskService(
    private val taskRepository: SimpleTaskRepository,
    private val mongoTemplate: MongoTemplate
) {
    fun getNumberOfSimpleTasks(count: Int): List<SimpleTaskDto> {
        val samples: SampleOperation = Aggregation.sample(count.toLong())
        val aggregation: Aggregation = Aggregation.newAggregation(samples)
        val aggregationRes = mongoTemplate
            .aggregate(aggregation, Tables.TASK_TABLE, SimpleTask::class.java)
        return aggregationRes.mappedResults.map { SimpleTaskDto(it) }
    }

    fun getNumberOfSubjectTasks(count: Int, subjectId: Int): List<SimpleTaskDto> {
        val samples: SampleOperation = Aggregation.sample(count.toLong())
        val match: MatchOperation = Aggregation.match()
        val aggregation: Aggregation = Aggregation.newAggregation(samples)

    }

    fun getTaskById(taskId: String): SimpleTaskDto {
        return SimpleTaskDto(taskRepository.findById(ObjectId(taskId)).orElseThrow())
    }

    fun addTaskBySubject(task: NewTaskDto): SimpleTaskDto {

    }
}