package com.egecube.eduplatform.classesManagement.services

import com.egecube.eduplatform.classesManagement.repositories.GroupsRepository
import org.springframework.stereotype.Service

@Service
class FlowService(private val groupsRepository: GroupsRepository) {

    fun getById(id: Long) = groupsRepository.findById(id)

    fun getByFlowIdAndId(flowId: Long, id: Long) = groupsRepository.getByFlowIdAndId(flowId, id)

    fun getAllByFlowId(flowId: Long) = groupsRepository.getAllByFlowId(flowId)

}