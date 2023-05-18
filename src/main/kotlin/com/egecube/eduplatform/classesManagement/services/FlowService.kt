package com.egecube.eduplatform.classesManagement.services

import com.egecube.eduplatform.classesManagement.repositories.FlowRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class FlowService(
    private val flowRepository: FlowRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun getById(id: Long) = flowRepository.findById(id)

    fun getByFlowIdAndId(flowId: Long, id: Long) = flowRepository.getBySubjectIdAndId(flowId, id)

    fun getAllByFlowId(flowId: Long) = flowRepository.getAllBySubjectId(flowId)

}