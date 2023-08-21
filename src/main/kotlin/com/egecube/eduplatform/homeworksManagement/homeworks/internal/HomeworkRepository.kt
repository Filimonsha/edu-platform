package com.egecube.eduplatform.homeworksManagement.homeworks.internal

import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.Homework
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*


interface HomeworkRepository : MongoRepository<Homework, String> {
    fun findBy_id(_id: String): Optional<Homework>
    fun findAllByCreatorId(creatorId: String): List<Homework>

    fun findAllBySubjectIdAndSolversContains(subjectId: Long, solvers: MutableList<Long>):List<Homework>

}