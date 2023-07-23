package com.egecube.eduplatform.homeworksManagement.homeworks.internal

import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.HomeworkAnswer
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface HomeworkAnswerRepository : MongoRepository<HomeworkAnswer, String> {
    fun findBy_id(_id: String): Optional<HomeworkAnswer>

    fun findAllByHomeworkId(homeworkId: String): List<HomeworkAnswer>

    fun findAllBySolverId(solverId: Long): List<HomeworkAnswer>
}