package com.egecube.eduplatform.flowsManagement.services

import com.egecube.eduplatform.flowsManagement.domain.Flow
import com.egecube.eduplatform.flowsManagement.repositories.FlowRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class FlowService (private val flowRepository: FlowRepository){
    fun get(id: Long): Optional<Flow> = flowRepository.findById(id)
}