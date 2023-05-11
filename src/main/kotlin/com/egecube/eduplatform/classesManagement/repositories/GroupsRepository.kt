package com.egecube.eduplatform.classesManagement.repositories

import com.egecube.eduplatform.classesManagement.domain.Flow
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface GroupsRepository : JpaRepository<Flow, Long> {
    fun getAllByFlowId(flowId: Long): List<Flow>
    fun getByFlowIdAndId(flowId: Long, id: Long): Optional<Flow>

}