package com.egecube.eduplatform.flowsManagement.repositories

import com.egecube.eduplatform.flowsManagement.domain.FlowGroup
import org.springframework.data.jpa.repository.JpaRepository

interface GroupsRepository : JpaRepository<FlowGroup, Long>{
    fun getAllByFlowId(flowId:Long):List<FlowGroup>

}