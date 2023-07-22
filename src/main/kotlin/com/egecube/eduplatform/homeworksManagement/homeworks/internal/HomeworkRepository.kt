package com.egecube.eduplatform.homeworksManagement.homeworks.internal

import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.Homework
import org.springframework.data.mongodb.repository.MongoRepository


interface HomeworkRepository : MongoRepository<Homework, String> {

}