package com.egecube.eduplatform.homeworksManagement.homeworks.internal

import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.HomeworkAnswer
import org.springframework.data.mongodb.repository.MongoRepository

interface HomeworkAnswerRepository : MongoRepository<HomeworkAnswer, String> {
}