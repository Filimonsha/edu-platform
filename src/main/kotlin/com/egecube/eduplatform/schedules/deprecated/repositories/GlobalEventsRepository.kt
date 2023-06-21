package com.egecube.eduplatform.schedules.deprecated.repositories

import com.egecube.eduplatform.schedules.deprecated.events.GlobalEvent
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface GlobalEventsRepository : CrudRepository<GlobalEvent, Long> {
    fun findAllByNameContaining(query: String): List<GlobalEvent>

    fun findAllByTimeStampBetween(start: LocalDateTime, stop: LocalDateTime)

    fun findById(id: UUID): GlobalEvent?
    fun deleteGlobalEventById(id: UUID)

}