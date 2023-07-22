package com.egecube.eduplatform.tasksManagement.tasks.internal

import com.egecube.eduplatform.tasksManagement.tasks.SimpleTask
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository: MongoRepository<SimpleTask, Long>