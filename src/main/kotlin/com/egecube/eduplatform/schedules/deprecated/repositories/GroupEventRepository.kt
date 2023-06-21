package com.egecube.eduplatform.schedules.deprecated.repositories

import com.egecube.eduplatform.schedules.deprecated.events.GroupEvent
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface GroupEventRepository : CrudRepository<GroupEvent, Long> {
    fun findAllByNameContaining(query: String): List<GroupEvent>

    //TODO(Check class to GroupId)
    fun findAllByGroupsContaining(groupId: String): List<GroupEvent>
    fun findAllByGroupsContainingAndTimeStampBetween(
        groupId: String, start: LocalDateTime, stop: LocalDateTime
    )

    fun findById(id: UUID): GroupEvent?
    fun deleteGroupEventById(id: UUID)
}