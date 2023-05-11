package com.egecube.eduplatform.flowsManagement.repositories

import com.egecube.eduplatform.flowsManagement.domain.FlowGroup
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface GroupsRepository : JpaRepository<FlowGroup, Long> {
    fun getAllByFlowId(flowId: Long): List<FlowGroup>
    fun getByFlowIdAndId(flowId: Long, id: Long): Optional<FlowGroup>

}