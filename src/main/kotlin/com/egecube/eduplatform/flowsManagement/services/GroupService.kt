package com.egecube.eduplatform.flowsManagement.services

import com.egecube.eduplatform.flowsManagement.repositories.GroupsRepository
import org.springframework.stereotype.Service

@Service
class GroupService(private val groupsRepository: GroupsRepository) {

    fun getById(id: Long) = groupsRepository.findById(id)

    fun getAllByFlowId(flowId: Long) = groupsRepository.getAllByFlowId(flowId)

}