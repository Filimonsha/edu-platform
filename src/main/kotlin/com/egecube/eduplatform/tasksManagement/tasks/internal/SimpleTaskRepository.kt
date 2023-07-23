package com.egecube.eduplatform.tasksManagement.tasks.internal

import com.egecube.eduplatform.tasksManagement.tasks.SimpleTask
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface SimpleTaskRepository: MongoRepository<SimpleTask, ObjectId>