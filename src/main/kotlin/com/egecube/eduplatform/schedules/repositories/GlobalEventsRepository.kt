package com.egecube.eduplatform.schedules.repositories

import com.egecube.eduplatform.schedules.domain.events.GlobalEvent
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.UUID

@Repository
interface GlobalEventsRepository: CrudRepository<GlobalEvent, Long> {
    fun findAllByNameContaining(query: String): List<GlobalEvent>

    fun findAllByTimeStampBetween(start: LocalDateTime, stop: LocalDateTime)

    fun findById(id: UUID): GlobalEvent?
    fun deleteGlobalEventById(id: UUID)

}