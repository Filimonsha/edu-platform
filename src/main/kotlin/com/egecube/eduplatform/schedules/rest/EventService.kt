package com.egecube.eduplatform.schedules.rest

import com.egecube.eduplatform.schedules.repositories.EventRepository
import com.egecube.eduplatform.schedules.domain.Event
import org.springframework.stereotype.Service

@Service
class EventService(
    private val eventRepository: EventRepository
) {
    fun getAllEvents(): List<Event>  {
        return eventRepository.findAll() as ArrayList<Event>
    }
}