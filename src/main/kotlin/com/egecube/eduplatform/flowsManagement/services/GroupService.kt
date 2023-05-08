package com.egecube.eduplatform.flowsManagement.services

import com.egecube.eduplatform.flowsManagement.repositories.GroupsRepository

class GroupService(private val groupsRepository: GroupsRepository) {

    fun getById(id: Long) = groupsRepository.findById(id)

}