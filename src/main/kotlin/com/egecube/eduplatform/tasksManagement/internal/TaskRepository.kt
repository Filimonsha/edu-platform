package com.egecube.eduplatform.tasksManagement.internal

import com.egecube.eduplatform.tasksManagement.Task
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository: MongoRepository<Task, Long>