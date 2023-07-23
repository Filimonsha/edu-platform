package com.egecube.eduplatform.homeworksManagement.solvers.iternal

import com.egecube.eduplatform.homeworksManagement.solvers.iternal.domain.Solver
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface SolverRepository : MongoRepository<Solver, Long> {
    fun findBy_id(_id: Long): Optional<Solver>
}