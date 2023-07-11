package com.egecube.eduplatform.homeworksManagement.internal

import com.egecube.eduplatform.homeworksManagement.internal.domain.Task
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository : MongoRepository<Task, String> {
}