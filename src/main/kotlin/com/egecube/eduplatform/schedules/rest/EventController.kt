package com.egecube.eduplatform.schedules.rest

import com.egecube.eduplatform.schedules.consts.EventRoutes
import com.egecube.eduplatform.schedules.domain.Event
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EventController(
    private val eventService: EventService
) {
    @GetMapping(EventRoutes.EVENTS_ROUTE)
    fun getAllEvents(): ResponseEntity<List<Event>> {
        val events = eventService.getAllEvents()
        return if (events.isNotEmpty()) {
            ResponseEntity.ok().body(events)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}