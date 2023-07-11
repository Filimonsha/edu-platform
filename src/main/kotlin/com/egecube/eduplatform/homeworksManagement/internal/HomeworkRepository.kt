package com.egecube.eduplatform.homeworksManagement.internal

import com.egecube.eduplatform.homeworksManagement.internal.domain.HomeWork
import org.springframework.data.mongodb.repository.MongoRepository


interface HomeworkRepository : MongoRepository<HomeWork, String> {
}