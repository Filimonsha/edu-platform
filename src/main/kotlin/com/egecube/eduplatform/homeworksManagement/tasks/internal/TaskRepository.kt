package com.egecube.eduplatform.homeworksManagement.tasks.internal

import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.Task
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository : MongoRepository<Task, String> {
    fun findAllByHomeworkId(homeworkId: String): List<Task>
}