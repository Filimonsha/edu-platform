package com.egecube.eduplatform.schedules.deprecated.repositories

import com.egecube.eduplatform.schedules.deprecated.events.UserEvent
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface UserEventRepository : CrudRepository<UserEvent, Long> {
    fun findAllByNameContaining(query: String): List<UserEvent>

    //TODO(Check user id class)
    fun findAllByAssignedTo(id: Long): List<UserEvent>
    fun findAllByAssignedToAndTimeStampBetween(
        id: Long, start: LocalDateTime, stop: LocalDateTime
    )

    fun findById(id: UUID): UserEvent?
    fun deleteUserEventById(id: UUID)
}