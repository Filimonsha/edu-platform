package com.egecube.eduplatform.classesManagement.repositories

import com.egecube.eduplatform.classesManagement.domain.Flow
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FlowRepository : JpaRepository<Flow, Long> {
    fun getAllBySubjectId(flowId: Long): List<Flow>
    fun getBySubjectIdAndId(flowId: Long, id: Long): Optional<Flow>

}