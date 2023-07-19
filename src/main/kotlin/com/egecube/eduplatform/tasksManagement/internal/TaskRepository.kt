package com.egecube.eduplatform.tasksManagement.internal

import com.egecube.eduplatform.tasksManagement.domain.SimpleTask
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository: MongoRepository<SimpleTask, Long>