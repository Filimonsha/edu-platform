package com.egecube.eduplatform.homeworksManagement.internal

import com.egecube.eduplatform.homeworksManagement.internal.domain.HomeworkAnswer
import org.springframework.data.mongodb.repository.MongoRepository

interface HomeworkAnswerRepository : MongoRepository<HomeworkAnswer, String> {
}