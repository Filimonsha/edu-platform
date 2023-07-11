package com.egecube.eduplatform.tasksManagement.internal

import com.egecube.eduplatform.tasksManagement.SimpleTask
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository: MongoRepository<SimpleTask, Long>