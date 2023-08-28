package com.egecube.eduplatform.tasksManagement.tasks

import com.egecube.eduplatform.tasksManagement.tasks.consts.Tables
import com.egecube.eduplatform.tasksManagement.tasks.dto.NewTaskDto
import com.egecube.eduplatform.tasksManagement.tasks.internal.SimpleTaskRepository
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Service


@Service
class SimpleTaskService(
    private val taskRepository: SimpleTaskRepository,
    private val mongoTemplate: MongoTemplate
) {
    fun getNumberOfSimpleTasks(count: Int): List<SimpleTask> {
        val samples = Aggregation.sample(count.toLong())
        val aggregation = Aggregation.newAggregation(samples)
        val aggregationRes = mongoTemplate
            .aggregate(aggregation, Tables.TASK_TABLE, SimpleTask::class.java)
        return aggregationRes.mappedResults
    }

    fun getNumberOfSubjectTasks(count: Int, subjectId: Int): List<SimpleTask> {
        val samples = Aggregation.sample(count.toLong())
        val match = Aggregation.match(Criteria("subjectId").`is`(subjectId))
        val aggregation: Aggregation = Aggregation.newAggregation(samples, match)
        val aggregationRes = mongoTemplate
            .aggregate(aggregation, Tables.TASK_TABLE, SimpleTask::class.java)
        return aggregationRes.mappedResults
    }

    fun getTaskById(taskId: String): SimpleTaskDto {
        return SimpleTaskDto(taskRepository.findById(ObjectId(taskId)).orElseThrow())
    }

    fun addTaskBySubject(task: NewTaskDto): SimpleTaskDto {
        val newTask = SimpleTask(
            subjectId = task.subjectId,
            desc = task.desc,
            answers = task.answers,
            rightAnswer = task.rightAnswer,
            taskNum = task.taskNum
        )
        return SimpleTaskDto(taskRepository.save(newTask))
    }
}